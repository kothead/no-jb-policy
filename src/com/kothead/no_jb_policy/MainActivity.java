package com.kothead.no_jb_policy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity implements TabListener {

    private static final String LOG_TAG = "main_activity";
    
    private ActionBar mActionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        hackJBPolicy();
        
        createTab(R.drawable.icon_tab1);
        createTab(R.drawable.icon_tab2);
        createTab(R.drawable.icon_tab3);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction transaction) {
        
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction transaction) {
        
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
        
    }
    
    private void createTab(int res) {
        Tab tab = mActionBar.newTab();
        tab.setIcon(res);
        tab.setTabListener(this);
        mActionBar.addTab(tab);
    }

    /**
     * forces container to set height
     */
    private void hackJBPolicy() {
        View container = findScrollingTabContainer();
        if (container == null) return;
        
        try {
            int height = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
            Method method = container.getClass()
                    .getDeclaredMethod("setContentHeight", Integer.TYPE);
            method.invoke(container, height);
        } catch (NoSuchMethodException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage(), e);
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage(), e);
        } catch (IllegalAccessException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage(), e);
        } catch (InvocationTargetException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage(), e);
        }
    }
    
    private View findScrollingTabContainer() {
        View decor = getWindow().getDecorView();
        int containerId = getResources().getIdentifier("action_bar_container", "id", "android");

        // check if appcompat library is used
        if (containerId == 0) {
            containerId = R.id.action_bar_container;
        }
        
        FrameLayout container = (FrameLayout) decor.findViewById(containerId);

        for (int i = 0; i < container.getChildCount(); i++) {
            View scrolling = container.getChildAt(container.getChildCount() - 1);
            String simpleName = scrolling.getClass().getSimpleName(); 
            if (simpleName.equals("ScrollingTabContainerView")) return scrolling;
        }
        
        return null;
    }
}
