package br.com.picasso.picassohouse.ui.features

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import br.com.picasso.picassohouse.R

class NavigationController (
        val activity: AppCompatActivity
){

    fun presentFragment(fragment: Fragment) {
        this.clearStack()

        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.content, fragment)
        transaction.commit()
    }


    fun pushFragment(fragment: Fragment) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(FRAGMENT_STACK_KEY)
        transaction.commit()
    }

    fun popFragment() {
        activity.supportFragmentManager.popBackStack()
    }

    fun clearStack() {
        val fm = activity.supportFragmentManager
        fm.popBackStackImmediate(FRAGMENT_STACK_KEY, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    companion object {
        private val FRAGMENT_STACK_KEY = "Fragments_stack"
    }
}