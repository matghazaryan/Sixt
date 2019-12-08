package com.mg.android.list.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.mg.android.list.R
import com.mg.android.list.views.ListAdapter
import com.mg.android.model.Car
import com.mg.android.repository.utils.Resource

object ListBinding {

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T>showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
        Log.d(ListBinding::class.java.simpleName, "Resource: $resource")
        if (resource != null) view.isRefreshing = resource.status == Resource.Status.LOADING
    }

    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<List<Car>>?) {
        with(recyclerView.adapter as ListAdapter) {
            resource?.data?.let { updateData(it) }
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url)
            .error(R.drawable.ic_directions_car_error)
            .placeholder(R.drawable.ic_directions_car)
            .into(view)
    }

    @BindingAdapter("app:showWhenEmptyList")
    @JvmStatic fun showMessageErrorWhenEmptyList(view: View, resource: Resource<List<Car>>?) {
        if (resource!=null) {
            view.visibility = if (resource.status == Resource.Status.ERROR
                && resource.data != null
                && resource.data!!.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}