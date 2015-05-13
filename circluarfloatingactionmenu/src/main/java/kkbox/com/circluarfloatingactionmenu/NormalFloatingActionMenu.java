package kkbox.com.circluarfloatingactionmenu;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kkbox.com.circluarfloatingactionmenu.animation.DefaultAnimationHandler;
import kkbox.com.circluarfloatingactionmenu.animation.MenuAnimationHandler;

public class NormalFloatingActionMenu extends FloatingActionMenu{

    public NormalFloatingActionMenu(View mainActionView, int startAngle, int endAngle, int radius, List<Item> subActionItems, MenuAnimationHandler animationHandler, boolean animated, MenuStateChangeListener stateChangeListener, boolean systemOverlay) {
        super(mainActionView, startAngle, endAngle, radius, subActionItems, animationHandler, animated, stateChangeListener, systemOverlay);
    }

    @Override
    protected Point calculateItemPositions() {
        final Point center = getActionViewCenter();
        for (int i = 0; i < subActionItems.size(); i++) {
            subActionItems.get(i).x = center.x - subActionItems.get(i).width / 2;
            subActionItems.get(i).y = center.y - (i) * getRadius() - subActionItems.get(i).height / 2 - mainActionView.getMeasuredHeight();
        }
        return center;
    }

    public static class Builder {

        private int startAngle;
        private int endAngle;
        private int radius;
        private View actionView;
        private List<Item> subActionItems;
        private MenuAnimationHandler animationHandler;
        private boolean animated;
        private MenuStateChangeListener stateChangeListener;
        private boolean systemOverlay;

        public Builder(Context context, boolean systemOverlay) {
            subActionItems = new ArrayList<Item>();
            // Default settings
            radius = context.getResources().getDimensionPixelSize(R.dimen.action_menu_radius);
            startAngle = 180;
            endAngle = 270;
            animationHandler = new DefaultAnimationHandler();
            animated = true;
            this.systemOverlay = systemOverlay;
        }

        public Builder(Context context) {
            this(context, false);
        }

        public Builder setStartAngle(int startAngle) {
            this.startAngle = startAngle;
            return this;
        }

        public Builder setEndAngle(int endAngle) {
            this.endAngle = endAngle;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder addSubActionView(View subActionView, int width, int height) {
            subActionItems.add(new Item(subActionView, width, height));
            return this;
        }

        /**
         * Adds a sub action view that is already alive, but not added to a parent View.
         * @param subActionView a view for the menu
         * @return the builder object itself
         */
        public Builder addSubActionView(View subActionView) {
            if(systemOverlay) {
                throw new RuntimeException("Sub action views cannot be added without " +
                        "definite width and height. Please use " +
                        "other methods named addSubActionView");
            }
            return this.addSubActionView(subActionView, 0, 0);
        }

        /**
         * Inflates a new view from the specified resource id and adds it as a sub action view.
         * @param resId the resource id reference for the view
         * @param context a valid context
         * @return the builder object itself
         */
        public Builder addSubActionView(int resId, Context context) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(resId, null, false);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            return this.addSubActionView(view, view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        /**
         * Sets the current animation handler to the specified MenuAnimationHandler child
         * @param animationHandler a MenuAnimationHandler child
         * @return the builder object itself
         */
        public Builder setAnimationHandler(MenuAnimationHandler animationHandler) {
            this.animationHandler = animationHandler;
            return this;
        }

        public Builder enableAnimations() {
            animated = true;
            return this;
        }

        public Builder disableAnimations() {
            animated = false;
            return this;
        }

        public Builder setStateChangeListener(MenuStateChangeListener listener) {
            stateChangeListener = listener;
            return this;
        }

        public Builder setSystemOverlay(boolean systemOverlay) {
            this.systemOverlay = systemOverlay;
            return this;
        }

        /**
         * Attaches the whole menu around a main action view, usually a button.
         * All the calculations are made according to this action view.
         * @param actionView
         * @return the builder object itself
         */
        public Builder attachTo(View actionView) {
            this.actionView = actionView;
            return this;
        }

        public NormalFloatingActionMenu build() {
            return new NormalFloatingActionMenu(actionView,
                    startAngle,
                    endAngle,
                    radius,
                    subActionItems,
                    animationHandler,
                    animated,
                    stateChangeListener,
                    systemOverlay);
        }
    }
}
