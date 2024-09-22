package com.beaconfire.quizproject.controller;

import com.beaconfire.quizproject.dao.ChoiceDao;
import com.beaconfire.quizproject.dao.QuestionDao;
import com.beaconfire.quizproject.dao.UserResultDao;
import com.beaconfire.quizproject.model.Choice;
import com.beaconfire.quizproject.model.Question;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.*;


@Controller
public class QuizController {
    @Autowired
    private final QuestionDao questionDao;
    private  final ChoiceDao choiceDao;
    private  final UserResultDao userResultDao;


    public QuizController(QuestionDao questionDao, ChoiceDao choiceDao, UserResultDao userResultDao) {
        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
        this.userResultDao = userResultDao;
    }


    @GetMapping("/question")
    public String showQuestion(@RequestParam("id") int questionId,
                               Model model) {


        Question question = questionDao.getQuestionById(questionId);
        List<Choice> choices = choiceDao.getChoicesByQuestionId(questionId);
        List<Question> allQuestions = questionDao.getAllQuestions();

        model.addAttribute("question", question);
        model.addAttribute("choices", choices);
        model.addAttribute("allQuestions", allQuestions);
        model.addAttribute("currentQuestionIndex", questionId - 1); //
        // current question index
        System.out.println("Fetched Choice ID: " + question.getId());  // 打印获取到的 ID

        return "question";
    }

    @PostMapping("/save-answer")
    public String SaveAnswer(
                               @RequestParam("questionId") int questionId,
                               @RequestParam("choiceId") int choiceId,
                               HttpSession session) {

        Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
        if (userAnswers == null) {
            userAnswers = new HashMap<>();
        }
        Integer userId = (Integer) session.getAttribute("userId");
        userAnswers.put(questionId, choiceId);
        session.setAttribute("userAnswers", userAnswers);
        if(questionId == 3 && userAnswers.size() == 3){
            saveUserResultToDatabase(userId, userAnswers);
            List<List<String>> colors = generateColors(userAnswers);
            session.setAttribute("userColors", colors);

            return "redirect:/result";

        }
        return "redirect:/question?id=" + (questionId + 1);  // 跳转到下一个问题
    }

    @GetMapping("/result")
    public String showResult(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        Map<Integer, Integer> userAnswers = (Map<Integer, Integer>) session.getAttribute("userAnswers");
        List<List<String>> colorResult =
                ( List<List<String>>)session.getAttribute("userColors");
                model.addAttribute("userId", userId);
        Map<String, String> formattedAnswers = new HashMap<>();
       if(userAnswers != null){
        for (Map.Entry<Integer, Integer> entry : userAnswers.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (key == 1) {
                switch (value) {
                    case 1:
                        formattedAnswers.put("Season", "Spring");
                        break;
                    case 2:
                        formattedAnswers.put("Season", "Summer");
                        break;
                    case 3:
                        formattedAnswers.put("Season", "Autumn");
                        break;
                    case 4:
                        formattedAnswers.put("Season", "Winter");
                        break;
                    default:
                        formattedAnswers.put("Season", "Unknown");
                        break;
                }
            } else if (key == 2) {
                formattedAnswers.put("Theme", value.equals(5) ? "Lighter" :
                        "Darker");
            } else if (key == 3) {
                formattedAnswers.put("Elements", value.equals(7) ? "2 " +
                        "Elements" : "3 Elements");
            }
        }

        }
        model.addAttribute("formattedAnswers", formattedAnswers);
        model.addAttribute("colorResult",colorResult);

        return "result";
    }


    @PostMapping("/restQuiz")
    public String restQuiz(HttpSession session){
        Map<Integer,Integer> userAnswer = ( Map<Integer,Integer> )session.getAttribute("userAnswers");
        if(userAnswer != null && userAnswer.size() > 0){
            session.removeAttribute("userAnswer");
        }
        return "start";
    }



    //colorgenerate

    // Method to get the appropriate color list based on season and theme
    private List<String> getSeasonalColors(String season, String theme) {

        // Color lists for each season and theme
        List<String> springLightColors = Arrays.asList(
                "#FFC0CB", "#FFB6C1", "#FF69B4", // Pinks
                "#98FB98", "#00FF7F", "#32CD32", "#00FA9A", "#7FFFD4", "#66CDAA", // Greens
                "#F5F5DC", "#F5DEB3", "#FFF8DC", "#FFEBCD", "#FAFAD2", "#FFFFE0" // Beiges
        );
        List<String> springDarkColors = Arrays.asList(
                "#C71585", "#DB7093", "#FF1493", // Darker Pinks
                "#228B22", "#006400", "#2E8B57", "#008000", "#556B2F", "#006400", // Darker Greens
                "#D2B48C", "#8B4513", "#A0522D", "#CD853F", "#8B0000", "#B8860B" // Darker Beige and Browns
        );

        List<String> summerLightColors = Arrays.asList("#FFD700", "#FF4500", "#FFA500", "#FF6347", "#FF7F50", "#FFDAB9", "#FF69B4", "#FFB6C1", "#DB7093", "#FF00FF", "#EE82EE", "#BA55D3", "#DA70D6", "#C71585", "#DDA0DD");
        List<String> summerDarkColors = Arrays.asList("#8B0000", "#A52A2A", "#800000", "#B22222", "#DC143C", "#8B008B", "#4B0082", "#483D8B", "#2F4F4F", "#008B8B", "#4682B4", "#000080", "#191970", "#556B2F", "#006400");
        List<String> autumnLightColors = Arrays.asList("#FFA07A", "#FA8072", "#E9967A", "#F08080", "#CD5C5C", "#DC143C", "#B22222", "#FF0000", "#FF4500", "#FFD700", "#FFA500", "#FF8C00", "#FF6347", "#FF7F50", "#FF4500");
        List<String> autumnDarkColors = Arrays.asList("#8B0000", "#A52A2A", "#800000", "#B22222", "#DC143C", "#8B4513", "#A0522D", "#D2691E", "#CD853F", "#8B4513", "#A0522D", "#6B8E23", "#556B2F", "#8B4513", "#A0522D");
        List<String> winterLightColors = Arrays.asList("#00BFFF", "#1E90FF", "#4682B4", "#5F9EA0", "#6495ED", "#7B68EE", "#4169E1", "#0000FF", "#8A2BE2", "#4B0082", "#483D8B", "#8B4513", "#A52A2A", "#800000", "#000080");
        List<String> winterDarkColors = Arrays.asList("#191970", "#000080", "#00008B", "#483D8B", "#2F4F4F", "#008B8B", "#4682B4", "#5F9EA0", "#4B0082", "#8B4513", "#A52A2A", "#556B2F", "#8B4513", "#2E8B57", "#6B8E23");

        switch (season.toLowerCase()) {
            case "spring":
                return theme.equals("light") ? springLightColors : springDarkColors;
            case "summer":
                return theme.equals("light") ? summerLightColors : summerDarkColors;
            case "autumn":
                return theme.equals("light") ? autumnLightColors : autumnDarkColors;
            case "winter":
                return theme.equals("light") ? winterLightColors : winterDarkColors;
            default:
                throw new IllegalArgumentException("Invalid season or theme");
        }
    }

    // Method to pick random colors from the selected list
    private List<String> pickRandomColors(List<String> colors, int count) {
        Random random = new Random();
        List<String> selectedColors = new ArrayList<>();

        // Generate colors for each element (2 or 3)
        for (int i = 0; i < count; i++) { // count * 3 to pick 6 colors for
            // 2 elements, 9 colors for 3 elements
            int randomIndex = random.nextInt(colors.size()); // Random index smaller than the size of the list
            selectedColors.add(colors.get(randomIndex));
        }

        return selectedColors;
    }

    // Main method to generate color combinations based on user answers
    public List<List<String>> generateColors(Map<Integer, Integer> userAnswers) {
        String season = "";
        String theme = "";
        int numberOfElements = 0;

        // Determine the season, theme, and number of elements from user answers
        for (Map.Entry<Integer, Integer> pair : userAnswers.entrySet()) {
            int qid = pair.getKey();
            int cid = pair.getValue();
            if (qid == 1) {
                switch (cid) {
                    case 1:
                        season = "spring";
                        break;
                    case 2:
                        season = "summer";
                        break;
                    case 3:
                        season = "autumn";
                        break;
                    case 4:
                        season = "winter";
                        break;
                }
            } else if (qid == 2) {
                theme = (cid == 5) ? "light" : "dark";
            } else if (qid == 3) {
                numberOfElements = (cid == 7) ? 2 : 3;
            }
        }

        // Get the appropriate color list based on season and theme
        List<String> colors = getSeasonalColors(season, theme);
        List<List<String>> answers = new ArrayList<>();

        // Generate lists of colors based on the number of elements
        for (int i = 0; i < 3; i++) {
            answers.add(pickRandomColors(colors, numberOfElements));
        }

        return answers;
    }

    //colorgenerate


    private void saveUserResultToDatabase(int userId, Map<Integer, Integer> userAnswers) {
        for (Map.Entry<Integer, Integer> entry : userAnswers.entrySet()) {
            int questionId = entry.getKey();
            int choiceId = entry.getValue();
            userResultDao.saveUserResult(userId, questionId, choiceId);
            //
        }
    }


        private void saveAllUserAnswers(int userId, Map<Integer, Integer> userAnswers) {
        for (Map.Entry<Integer, Integer> entry : userAnswers.entrySet()) {
            int questionId = entry.getKey();
            int choiceId = entry.getValue();
            System.out.println(questionId + ":" +choiceId);
        }}



}
