package com.coderops.ui.home.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.HomeItem
import com.coderops.ui.home.HomeItemType
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.HomeRecyclerviewNowPlayingBinding
import com.coderops.ui.home.databinding.HomeRecyclerviewPopularMoviesBinding
import com.coderops.ui.home.databinding.HomeRecyclerviewPopularPeopleBinding
import com.coderops.ui.home.databinding.HomeRecyclerviewSliderBinding
import com.coderops.ui.home.databinding.HomeRecyclerviewTopRatedBinding
import com.coderops.ui.home.databinding.HomeRecyclerviewTrendingBinding
import com.coderops.viewmodel.home.HomeListener
import java.lang.Math.abs

class HomeAdapter(
    private var itemsHome: MutableList<HomeItem>,
    private val listener: HomeListener
) : BaseAdapter<HomeItem>(itemsHome, listener) {

    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HomeItemType.SLIDER.ordinal -> {
                SliderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_slider, parent, false
                    )
                )
            }

            HomeItemType.NOW_PLAYING.ordinal -> {
                NowPlayingViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_now_playing, parent, false
                    )
                )
            }

            HomeItemType.TRENDING.ordinal -> {
                TrendingViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_trending, parent, false
                    )
                )
            }

            HomeItemType.TOP_RATED.ordinal -> {
                TopRatedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_top_rated, parent, false
                    )
                )
            }

            HomeItemType.POPULAR_PEOPLE.ordinal -> {
                PopularPeopleViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_popular_people, parent, false
                    )
                )
            }

            HomeItemType.POPULAR_MOVIES.ordinal -> {
                PopularMoviesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_popular_movies, parent, false
                    )
                )
            }


            else -> throw Exception("Mimo")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is SliderViewHolder -> bindSlider(holder, position)
            is NowPlayingViewHolder -> bindNowPlaying(holder, position)
            is TrendingViewHolder -> bindTrending(holder, position)
            is TopRatedViewHolder -> bindTopRated(holder, position)
            is PopularPeopleViewHolder -> bindPopularPeople(holder, position)
            is PopularMoviesViewHolder -> bindPopularMovies(holder, position)


        }
    }

    fun setItem(item: HomeItem) {
        val newItems = itemsHome.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<HomeItem>) {
        itemsHome = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }

    private fun bindSlider(holder: SliderViewHolder, position: Int) {
        val upComing = itemsHome[position] as HomeItem.Slider
        val viewPager = holder.binding.viewPager
        val upComingAdapter = UpComingAdapter(upComing.list, listener)
        setupViewPager(viewPager, upComingAdapter)
        registerPageChangeCallback(viewPager)
        setSliderPageTransformer(viewPager)
        holder.binding.item = upComing
    }

    private fun setupViewPager(viewPager: ViewPager2, adapter: RecyclerView.Adapter<*>) {
        viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = 3
        }
    }

    private fun registerPageChangeCallback(viewPager: ViewPager2) {
        val handler = Handler(Looper.myLooper()!!)
        val runnable = Runnable { viewPager.currentItem += 1 }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 6000)
            }
        })
    }

    private fun setSliderPageTransformer(viewPager: ViewPager2) {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(16))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager.setPageTransformer(transformer)
    }

    private fun bindNowPlaying(holder: NowPlayingViewHolder, position: Int) {
        val nowPlaying = itemsHome[position] as HomeItem.NowPlaying
        val adapter = NowPlayingAdapter(nowPlaying.list, listener)
        holder.binding.recyclerViewNowPlaying.adapter = adapter
        holder.binding.item = nowPlaying
    }

    private fun bindTopRated(holder: TopRatedViewHolder, position: Int) {
        val topRated = itemsHome[position] as HomeItem.TopRated
        val adapter = TopRatedAdapter(topRated.list, listener)
        holder.binding.recyclerViewTopRated.adapter = adapter
        holder.binding.item = topRated
        holder.binding.listener = listener
    }

    private fun bindTrending(holder: TrendingViewHolder, position: Int) {
        val trending = itemsHome[position] as HomeItem.Trending
        val adapter = TrendingAdapter(trending.list, listener)
        holder.binding.recyclerViewTrending.adapter = adapter
        holder.binding.item = trending
        holder.binding.listener = listener
    }

    private fun bindPopularPeople(holder: PopularPeopleViewHolder, position: Int) {
        val popularPeople = itemsHome[position] as HomeItem.PopularPeople
        val adapter = PopularPeopleAdapter(popularPeople.list, listener)
        holder.binding.recyclerViewPopularPeople.adapter = adapter
        holder.binding.item = popularPeople
    }

    private fun bindPopularMovies(holder: PopularMoviesViewHolder, position: Int) {
        val popularMovies = itemsHome[position] as HomeItem.PopularMovies
        val adapter = PopularMoviesAdapter(popularMovies.list, listener)
        holder.binding.recyclerViewPopularMovies.adapter = adapter
        holder.binding.item = popularMovies
        holder.binding.listener = listener
    }


    override fun getItemViewType(position: Int): Int = itemsHome[position].type.ordinal

    class SliderViewHolder(val binding: HomeRecyclerviewSliderBinding) : BaseViewHolder(binding)
    class NowPlayingViewHolder(val binding: HomeRecyclerviewNowPlayingBinding) :
        BaseViewHolder(binding)

    class TrendingViewHolder(val binding: HomeRecyclerviewTrendingBinding) : BaseViewHolder(binding)
    class TopRatedViewHolder(val binding: HomeRecyclerviewTopRatedBinding) : BaseViewHolder(binding)
    class PopularPeopleViewHolder(val binding: HomeRecyclerviewPopularPeopleBinding) :
        BaseViewHolder(binding)

    class PopularMoviesViewHolder(val binding: HomeRecyclerviewPopularMoviesBinding) :
        BaseViewHolder(binding)


}