package me.reezy.cosmo.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), BaseView {

    private var initialized: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSetupUI()
    }

    override fun onResume() {
        super.onResume()
        if (!initialized && !isHidden) {
            onLoadData(true)
            initialized = true
        }
    }

    override fun onLoadData(isRefresh: Boolean) {
    }
}