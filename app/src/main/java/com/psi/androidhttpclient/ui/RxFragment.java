package com.psi.androidhttpclient.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import com.psi.androidhttpclient.R;
import com.psi.androidhttpclient.adapter.ZhuangbiImage;
import com.psi.androidhttpclient.adapter.ZhuangbiListAdapter;
import com.psi.androidhttpclient.utils.Network1;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";

  // TODO: Rename and change types of parameters
  private String mParam1;
  @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
  @Bind(R.id.gridRv) RecyclerView gridRv;

  ZhuangbiListAdapter adapter = new ZhuangbiListAdapter();
  private OnFragmentInteractionListener mListener;
  protected Subscription subscription;

  // TODO: Rename and change types and number of parameters
  public static RxFragment newInstance(String param1) {
    RxFragment fragment = new RxFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    fragment.setArguments(args);
    return fragment;
  }
  public RxFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
    }
  }
  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unsubscribe();
  }

  protected void unsubscribe() {
    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }
  Observer<List<ZhuangbiImage>> observer = new Observer<List<ZhuangbiImage>>() {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
      swipeRefreshLayout.setRefreshing(false);
      Toast.makeText(getActivity(),"返回的数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(List<ZhuangbiImage> images) {
      swipeRefreshLayout.setRefreshing(false);
      adapter.setImages(images);
    }
  };

  @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
  void onTagChecked(RadioButton searchRb, boolean checked) {
    if (checked) {
      unsubscribe();
      adapter.setImages(null);
      swipeRefreshLayout.setRefreshing(true);
      search(searchRb.getText().toString());
    }
  }

  private void search(String key) {
    subscription = Network1.getZhuangbiApi()
        .search(key)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
    onButtonPressed(null);
  }
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_rx, container, false);
    ButterKnife.bind(this, view);

    gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    gridRv.setAdapter(adapter);
    swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
    swipeRefreshLayout.setEnabled(false);

    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
