package com.coderops.viewmodel.common

import com.coderops.bases.BaseInteractionListener

interface AnswerListener: BaseInteractionListener {
    fun onClickAnswer(answerPosition: Int)
}