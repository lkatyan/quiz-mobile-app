package com.example.quizmobileapp

class QuestionsBank {

    companion object {
        fun getQuestions(questionsBank: QuestionsBank, selectedQuizName: String?): List<QuestionList> {
            return when (selectedQuizName) {
                "stars" -> questionsBank.starsQuestions()
                "animals" -> questionsBank.animalsQuestions()
                "music" -> questionsBank.musicQuestions()
                "plants" -> questionsBank.plantsQuestions()
                else -> questionsBank.starsQuestions()
            }
        }
    }

    private fun starsQuestions(): List<QuestionList> {
        var questionList: MutableList<QuestionList> = arrayListOf()
        var questionFirst = QuestionList(
            "Уран",
            " Юпитер",
            "Церера",
            "Сатурн",
            "Самая большая планета Солнечной системы?",
            "Юпитер",
            "")
        var questionSecond = QuestionList(
            "Пять",
            "Девять",
            "Семь",
            "Восемь",
            "Сколько всего планет в Солнечной системе?",
            "Восемь",
            "")
        var questionThird = QuestionList(
            "Два спутника",
            "Пять спутников",
            "У Марса нет спутников",
            "Четыре спутника",
            "Сколько спутников у Марса?",
            "Два спутника",
            "")
        var questionFourth = QuestionList(
            "Ио",
            "Фобос",
            "Европа",
            "Ганимед",
            "Самый большой спутник в Солнечной системе?",
            "Ганимед",
            "")
        questionList.add(questionFirst)
        questionList.add(questionSecond)
        questionList.add(questionThird)
        questionList.add(questionFourth)
        return questionList
    }

    private fun animalsQuestions(): List<QuestionList> {
        var questionList: MutableList<QuestionList> = arrayListOf()
        var questionFirst = QuestionList(
            "Верблюд",
            "Панда",
            "Коала",
            "Слон",
            "Какое животное обитает только в Китае?",
            "Панда",
            "")
        var questionSecond = QuestionList(
            "Альбатрос",
            "Журавль",
            "Коршун",
            "Орлан",
            "У какой птицы самый большой размах крыльев? ",
            "Альбатрос",
            "")
        var questionThird = QuestionList(
            "Лени",
            "Мудрости",
            "Доброты",
            "Терпения",
            "Символом чего считается сова?",
            "Мудрости",
            "")
        var questionFourth = QuestionList(
            "Лени",
            "Мудрости",
            "Доброты",
            "Терпения",
            "Символом чего считается программист?",
            "Лени",
            "")
        questionList.add(questionFirst)
        questionList.add(questionSecond)
        questionList.add(questionThird)
        questionList.add(questionFourth)
        return questionList
    }

    private fun musicQuestions(): List<QuestionList> {
        var questionList: MutableList<QuestionList> = arrayListOf()
        var questionFirst = QuestionList(
            "15",
            "22",
            "40",
            "30",
            "Сколько тональностей в музыке?",
            "30",
            "")
        var questionSecond = QuestionList(
            "Фа-Си-Ре-Соль",
            "Никак",
            "До-До-До-Ми",
            "Ми-До-До-До",
            "Как разрешается доминантсептаккорд?",
            "Ми-До-До-До",
            "")
        var questionThird = QuestionList(
            "Ля мажор",
            "До мажор",
            "Ми мажор",
            "Ре мажор",
            "Какая диезная тональность является третьей по счету в квинтовом круге?",
            "Ля мажор",
            "")
        var questionFourth = QuestionList(
            "Септима",
            "Секунда",
            "Квинта",
            "Терция",
            "Музыкальный интервал шириной в три ступени?",
            "Терция",
            "")
        questionList.add(questionFirst)
        questionList.add(questionSecond)
        questionList.add(questionThird)
        questionList.add(questionFourth)
        return questionList
    }

    private fun plantsQuestions(): List<QuestionList> {
        var questionList: MutableList<QuestionList> = arrayListOf()
        var questionFirst = QuestionList(
            "Чабрец",
            "Женьшень",
            "Имбирь",
            "Родиола розовая",
            "Какое травянистое растение известно под названием «золотой корень»?",
            "Родиола розовая",
            "")
        var questionSecond = QuestionList(
            "Паслёновые",
            "Злаковые",
            "Бобовые",
            "Капустные",
            "К какому семейству травянистых растений относится белена?",
            "Паслёновые",
            "")
        var questionThird = QuestionList(
            "Граб",
            "Ива",
            "Пихта",
            "Елка",
            "Какое растение относится к вечнозелёным голосеменным?",
            "Пихта",
            "")
        var questionFourth = QuestionList(
            "Сосна",
            "Кедр",
            "Лиственница",
            "Кипарис",
            "Какое из хвойных деревьев известно как пиния?",
            "Сосна",
            "")
        questionList.add(questionFirst)
        questionList.add(questionSecond)
        questionList.add(questionThird)
        questionList.add(questionFourth)
        return questionList
    }
}