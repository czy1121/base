package me.reezy.cosmo.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        setSupportActionBar(null)
    }

    override fun onResume() {
        foregroundRef = WeakReference(this)
        super.onResume()

    }


    companion object {
        private var foregroundRef: WeakReference<BaseActivity?> = WeakReference(null)

        val foreground: BaseActivity?
            get() = foregroundRef.get()?.let {
                if (it.isFinishing || it.isDestroyed) null else it
            }
    }

}