package com.example.whattowatch.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whattowatch.databinding.FragmentFavoritesBinding
import com.example.whattowatch.view.adapters.FavoriteShowAdapter
import com.example.whattowatch.viewmodel.FavoriteShowViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoriteShowViewModel by viewModels()
    private lateinit var favoriteShowAdapter: FavoriteShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeFavorites()
    }

    private fun setupRecyclerView() {
        favoriteShowAdapter = FavoriteShowAdapter { favoriteShow ->
            favoritesViewModel.removeFavorite(favoriteShow)
        }

        binding.rvFavorites.apply {
            adapter = favoriteShowAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeFavorites() {
        favoritesViewModel.favoriteShows.observe(viewLifecycleOwner) { favoriteShows ->
            favoriteShowAdapter.updateList(favoriteShows)
            binding.emptyView.visibility =
                if (favoriteShows.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}