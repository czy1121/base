package me.reezy.cosmo.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId), BaseView {

    fun requireView(): View {
        return findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
    }

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