package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var asteroidAdapter: AsteroidAdapter
    private val viewModelFactory: MainViewModelFactory by lazy {
        MainViewModelFactory(requireContext())
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAsteroidsOfTheDay()
        viewModel.getPictureOfTheDay()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        asteroidAdapter = AsteroidAdapter()
        binding.asteroidRecycler.adapter = asteroidAdapter
        addMenuOptions()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            it?.let { isLoading ->
                binding.statusLoadingWheel.visibility =
                    if(isLoading) View.VISIBLE
                    else View.GONE
            }
        }

        viewModel.asteroidsLiveData.observe(viewLifecycleOwner) {
            it?.let(asteroidAdapter::submitList)
        }

        viewModel.picOfTheDay.observe(viewLifecycleOwner) {
            it?.let { pic ->
                if (pic.mediaType == MEDIA_TYPE_IMAGE) {
                    Picasso.with(requireContext())
                        .load(pic.url)
                        .placeholder(R.drawable.placeholder_picture_of_day)
                        .error(R.drawable.placeholder_picture_of_day)
                        .into(binding.imageOfTheDay)

                    // set image content description
                    binding.imageOfTheDay.contentDescription = getString(
                        R.string.nasa_picture_of_day_content_description_format,
                        pic.title
                    )
                }
            }
        }
    }


    private fun addMenuOptions() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.show_all_menu,
                    R.id.show_buy_menu,
                    R.id.show_rent_menu -> {
                        filterBySelectedMenuOption(menuItem.itemId)
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner)
    }

    private fun filterBySelectedMenuOption(menuId: Int) {
        // TODO filter list by menu
    }

    companion object {
        private const val MEDIA_TYPE_IMAGE = "image"
    }

}
