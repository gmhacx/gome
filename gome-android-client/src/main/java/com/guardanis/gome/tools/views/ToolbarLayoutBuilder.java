package com.guardanis.gome.tools.views;

import android.graphics.Color;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.guardanis.gome.R;
import com.guardanis.imageloader.views.SVGImageView;

public class ToolbarLayoutBuilder {

    private Toolbar toolbar;

    public ToolbarLayoutBuilder(Toolbar toolbar, int inflatedLayoutResId) {
        this.toolbar = toolbar;

        toolbar.removeAllViews();
        toolbar.addView(LayoutInflater.from(toolbar.getContext())
                .inflate(inflatedLayoutResId, toolbar, false));

        applyInsetChanges();
    }

    private void applyInsetChanges() {
        try {
            toolbar.setPadding(0, 0, 0, 0); // Fix for tablets
            toolbar.setContentInsetsAbsolute(0, 0);
        }
        catch (Throwable e) { e.printStackTrace(); }
    }

    public ToolbarLayoutBuilder addTitle(int textResId, View.OnClickListener onClickListener) {
        return addTitle(toolbar.getContext().getString(textResId), onClickListener);
    }

    public ToolbarLayoutBuilder addTitle(String text, View.OnClickListener onClickListener) {
        TextView textTitle = inflateTitleText();
        textTitle.setText(text);
        textTitle.setOnClickListener(onClickListener);

        addTitle(textTitle);

        return this;
    }

    public ToolbarLayoutBuilder addTitleSvg(String assetFileName, View.OnClickListener onClickListener) {
        SVGImageView svg = buildSvgImageView(assetFileName, onClickListener);
        addTitle(svg);

        return this;
    }

    public ToolbarLayoutBuilder addOptionText(String text, View.OnClickListener onClickListener) {
        TextView textOption = buildTextOption(text, onClickListener);
        addOption(textOption);

        return this;
    }

    public ToolbarLayoutBuilder addOptionSvg(String assetFileName, View.OnClickListener onClickListener) {
        SVGImageView svg = buildSvgImageView(assetFileName, onClickListener);
        addOption(svg);

        return this;
    }

    public ToolbarLayoutBuilder addOptionImage(int resId, View.OnClickListener onClickListener) {
        SVGImageView svg = inflateOptionImage();

        svg.setImageResource(resId);
        svg.setOnClickListener(onClickListener);

        addOption(svg);

        return this;
    }

    public SVGImageView buildSvgImageView(String assetFileName, View.OnClickListener onClickListener) {
        SVGImageView svg = inflateOptionImage();
        svg.setImageAsset(assetFileName);
        svg.setOnClickListener(onClickListener);

        return svg;
    }

    public TextView buildTextOption(String text) {
        return buildTextOption(text, null);
    }

    public TextView buildTextOption(String text, @Nullable View.OnClickListener onClickListener) {
        TextView textView = inflateOptionText();
        textView.setText(text);
        textView.setOnClickListener(onClickListener);

        if (onClickListener != null)
            ViewHelper.setBackgroundResourceAndKeepPadding(textView, R.drawable.base__button_transparent);

        return textView;
    }

    public TextView inflateTitleText() {
        return (TextView) LayoutInflater.from(toolbar.getContext())
                .inflate(R.layout.base__toolbar_title_text, toolbar, false);
    }

    public TextView inflateOptionText() {
        return (TextView) LayoutInflater.from(toolbar.getContext())
                .inflate(R.layout.base__toolbar_option_text, toolbar, false);
    }

    public SVGImageView inflateOptionImage() {
        return (SVGImageView) LayoutInflater.from(toolbar.getContext())
                .inflate(R.layout.base__toolbar_option_svg, toolbar, false);
    }

    public View addOption(View view) {
        ((LinearLayout) toolbar.findViewById(R.id.base__toolbar_parent))
                .addView(view);

        return view;
    }

    public View addTitle(View view) {
        ((LinearLayout) toolbar.findViewById(R.id.base__toolbar_title_parent))
                .addView(view);

        return view;
    }

    public LinearLayout getParent() {
        return (LinearLayout) toolbar.findViewById(R.id.base__toolbar_parent);
    }

    public ToolbarLayoutBuilder setTransparentBackground() {
        return setBackgroundColor(Color.TRANSPARENT);
    }

    public ToolbarLayoutBuilder setBackgroundColor(int color) {
        getParent().setBackgroundColor(color);

        return this;
    }

    public ToolbarLayoutBuilder setBackgroundResource(int resourceId) {
        getParent().setBackgroundResource(resourceId);

        return this;
    }

    public ToolbarLayoutBuilder hide() {
        getParent().setVisibility(View.GONE);

        return this;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
