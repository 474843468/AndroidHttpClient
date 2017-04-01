package com.psi.androidhttpclient.ui;

import android.app.SearchManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.psi.androidhttpclient.R;

public class Main6Activity extends AppCompatActivity {
  private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main6);

    ImageView image1 = (ImageView) findViewById(R.id.imageView1);
    image1.setImageDrawable(
        TextDrawable.builder().buildRect("A", mColorGenerator.getRandomColor()));

    // 圆角为20dip的矩形TextDrawable
    ImageView image2 = (ImageView) findViewById(R.id.imageView2);
    image2.setImageDrawable(
        TextDrawable.builder().buildRoundRect("A", mColorGenerator.getRandomColor(), 20));

    // 圆形TextDrawable
    ImageView image3 = (ImageView) findViewById(R.id.imageView3);
    image3.setImageDrawable(
        TextDrawable.builder().buildRound("A", mColorGenerator.getRandomColor()));

    // 多字TextDrawable
    ImageView image4 = (ImageView) findViewById(R.id.imageView4);
    image4.setImageDrawable(
        TextDrawable.builder().buildRound("AB", mColorGenerator.getRandomColor()));

    // 描边10dip的圆角矩形TextDrawable
    ImageView image5 = (ImageView) findViewById(R.id.imageView5);
    image5.setImageDrawable(TextDrawable.builder()
        .beginConfig()
        .withBorder(10)
        .endConfig()
        .buildRoundRect("A", mColorGenerator.getRandomColor(), 20));

    // 设定字体和字体颜色的TextDrawable
    ImageView image6 = (ImageView) findViewById(R.id.imageView6);
    image6.setImageDrawable(TextDrawable.builder()
        .beginConfig()
        .textColor(Color.BLACK)
        .useFont(Typeface.DEFAULT)
        .fontSize(30)
        .bold()
        .toUpperCase()
        .endConfig()
        .buildRect("a", Color.RED));

    // 分栏的TextDrawable
    ImageView image7 = (ImageView) findViewById(R.id.imageView7);
    image7.setImageDrawable(get2ItemTextDrawable());

    // 动画TextDrawable
    ImageView image8 = (ImageView) findViewById(R.id.imageView8);
    image8.setImageDrawable(getAnimationTextDrawable());
  }

  // 两分栏的TextDrawable
  public Drawable get2ItemTextDrawable() {
    String leftText = "a";
    String rightText = "b";

    TextDrawable.IBuilder builder = TextDrawable.builder().rect();

    TextDrawable left = builder.build(leftText, mColorGenerator.getRandomColor());
    TextDrawable right = builder.build(rightText, mColorGenerator.getRandomColor());

    Drawable[] layerList = {
        new InsetDrawable(left, 0, 0, toPix(25), 0), new InsetDrawable(right, toPix(25), 0, 0, 0)
    };
    return new LayerDrawable(layerList);
  }

  // 动画TextDrawable
  public Drawable getAnimationTextDrawable() {
    TextDrawable.IBuilder builder = TextDrawable.builder().rect();
    AnimationDrawable animationDrawable = new AnimationDrawable();

    for (int i = 0; i < 10; i++) {
      TextDrawable frame = builder.build(i + "", mColorGenerator.getRandomColor());
      animationDrawable.addFrame(frame, 2000);
    }

    animationDrawable.setOneShot(false);
    animationDrawable.start();

    return animationDrawable;
  }

  // dip转化为pix
  public int toPix(int dip) {
    Resources resources = getResources();
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
        resources.getDisplayMetrics());
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.search_menu, menu);

    // Retrieve the SearchView and plug it into SearchManager
    SearchView searchView =
        (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

    EditText searchEditText =
        (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
    searchEditText.setTextColor(getResources().getColor(R.color.white));
    searchEditText.setHintTextColor(getResources().getColor(R.color.white));

    SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    return true;
  }
}
