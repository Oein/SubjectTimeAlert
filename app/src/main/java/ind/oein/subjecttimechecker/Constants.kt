package ind.oein.subjecttimechecker

import java.util.Calendar

class Subject {
    public var title: String;
    public var desc: String;

    constructor(title: String, desc: String) {
        this.title = title;
        this.desc = desc;
    }
}

class Constants {
    companion object {
        public val times = arrayListOf<Calendar>(
            getTime(8,  25),
            getTime(9,  10),
            getTime(10, 15),
            getTime(11, 10),
            getTime(13, 5),
            getTime(14, 0),
            getTime(14, 55)
        )
        public val subjects= arrayListOf<ArrayList<Subject>>(
            arrayListOf<Subject>(
                Subject("'국어' 시간이다.", ""),
                Subject("'과학' 시간이다.", ""),
                Subject("'수학' 시간이다.", ""),
                Subject("'사회' 시간이다.", ""),
                Subject("'주제1' 시간이다.", "각자 자신에게 맞는 장소로 이동하자."),
                Subject("'주제1' 시간이다.", "각자 자신에게 맞는 장소로 이동하자.")
            ),
            arrayListOf<Subject>(
                Subject("'스포츠' 시간이다.", ""),
                Subject("'진로와 직업' 시간이다.", ""),
                Subject("'체육' 시간이다.", ""),
                Subject("'국어' 시간이다.", ""),
                Subject("'영어' 시간이다.", ""),
                Subject("'주제2' 시간이다.", "각자 자신에게 맞는 장소로 이동하자."),
                Subject("'주제2' 시간이다.", "각자 자신에게 맞는 장소로 이동하자.")
            ),
            arrayListOf<Subject>(
                Subject("'수학' 시간이다.", ""),
                Subject("'영어' 시간이다.", ""),
                Subject("'도덕' 시간이다.", ""),
                Subject("'체육' 시간이다.", ""),
                Subject("'진탐' 시간이다.", ""),
                Subject("'진탐' 시간이다.", "")
            ),
            arrayListOf<Subject>(
                Subject("'기술과 가정' 시간이다.", ""),
                Subject("'국어' 시간이다.", ""),
                Subject("'미술' 시간이다.", "5층 미술실로 이동하자."),
                Subject("'도덕' 시간이다.", ""),
                Subject("'과학' 시간이다.", ""),
                Subject("'예체' 시간이다.",""),
                Subject("'예체' 시간이다.","")
            ),
            arrayListOf<Subject>(
                Subject("'과학' 시간이다.", ""),
                Subject("'음악' 시간이다.", "5층 음악실로 이동하자"),
                Subject("'기술과 가정' 시간이다.", ""),
                Subject("'수학' 시간이다.", ""),
                Subject("'사회' 시간이다.", ""),
                Subject("'정컴' 시간이다.", "컴퓨터실로 이동하자")
            ),
        )

        private fun getTime(hour: Int, min: Int): Calendar {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, min)
            cal.set(Calendar.SECOND, 0)
            print("MILIS / " + cal.timeInMillis.toString())
            return cal
        }
    }
}