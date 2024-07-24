package com.coderops.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coderops.ui.sound_when_play.SoundManager
import com.coderops.ui.trivia.R
import com.coderops.ui.trivia.databinding.FragmentGameHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeGameFragment : Fragment() {
    lateinit var binding: FragmentGameHomeBinding
    @Inject
    lateinit var soundManager: SoundManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_home, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        soundManager.stopSound()
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(HomeGameFragmentDirections.actionHomeGameFragmentToTypeGameFragment())
            soundManager.playSound(R.raw.sound)
        }


    }
}