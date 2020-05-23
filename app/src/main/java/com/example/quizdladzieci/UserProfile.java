package com.example.quizdladzieci;

public class UserProfile {

        public String userAge;
        public String userEmail;
        public String userName;



        public int userBestScoreQuiz ;
        public int userGoodAnswersQuiz ;
        public int userBadAnswersQuiz ;
        public int userBestScoreCalc;
        public int userGoodAnswersCalc;

        public int userBadAnswersCalc ;

        public int userTotalQuestionsQuiz;


    public UserProfile(){

        }


    public UserProfile(String userAge, String userEmail, String userName, int userBestScoreQuiz, int userGoodAnswersQuiz, int userBadAnswersQuiz, int userBestScoreCalc, int userGoodAnswersCalc, int userBadAnswersCalc, int userTotalQuestionsQuiz) {
            this.userAge = userAge;
            this.userEmail = userEmail;
            this.userName = userName;
            this.userBestScoreQuiz = userBestScoreQuiz;
            this.userGoodAnswersQuiz = userGoodAnswersQuiz;
            this.userBadAnswersQuiz = userBadAnswersQuiz;
            this.userBestScoreCalc = userBestScoreCalc;
            this.userGoodAnswersCalc = userGoodAnswersCalc;
            this.userBadAnswersCalc = userBadAnswersCalc;
            this.userTotalQuestionsQuiz = userTotalQuestionsQuiz;
        }

        public String getUserAge() {
            return userAge;
        }

        public void setUserAge(String userAge) {
            this.userAge = userAge;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    public int getUserBestScoreQuiz() {
        return userBestScoreQuiz;
    }

    public void setUserBestScoreQuiz(int userBestScoreQuiz) {
        this.userBestScoreQuiz = userBestScoreQuiz;
    }

    public int getUserGoodAnswersQuiz() {
        return userGoodAnswersQuiz;
    }

    public void setUserGoodAnswersQuiz(int userGoodAnswersQuiz) {
        this.userGoodAnswersQuiz = userGoodAnswersQuiz;
    }

    public int getUserBadAnswersQuiz() {
        return userBadAnswersQuiz;
    }

    public void setUserBadAnswersQuiz(int userBadAnswersQuiz) {
        this.userBadAnswersQuiz = userBadAnswersQuiz;
    }

    public int getUserBestScoreCalc() {
        return userBestScoreCalc;
    }

    public void setUserBestScoreCalc(int userBestScoreCalc) {
        this.userBestScoreCalc = userBestScoreCalc;
    }

    public int getUserGoodAnswersCalc() {
        return userGoodAnswersCalc;
    }

    public void setUserGoodAnswersCalc(int userGoodAnswersCalc) {
        this.userGoodAnswersCalc = userGoodAnswersCalc;
    }

    public int getUserBadAnswersCalc() {
        return userBadAnswersCalc;
    }

    public void setUserBadAnswersCalc(int userBadAnswersCalc) {
        this.userBadAnswersCalc = userBadAnswersCalc;
    }

    public int getUserTotalQuestionsQuiz() {
        return userTotalQuestionsQuiz;
    }

    public void setUserTotalQuestionsQuiz(int userTotalQuestions) {
        this.userTotalQuestionsQuiz = userTotalQuestionsQuiz;
    }



}

