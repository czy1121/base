package me.reezy.cosmo.base

interface BaseView {
    fun onSetupUI()
    fun onLoadData(isRefresh: Boolean)
}