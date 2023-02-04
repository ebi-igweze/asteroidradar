package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.asteroidList
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val asteroidAdapter = AsteroidAdapter()
        asteroidAdapter.submitList(asteroidList)

        binding.asteroidRecycler.adapter = asteroidAdapter
        addMenuOptions()
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


}
