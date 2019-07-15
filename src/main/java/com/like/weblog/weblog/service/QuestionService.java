package com.like.weblog.weblog.service;

import com.like.weblog.weblog.dto.PageQuestionDTO;
import com.like.weblog.weblog.dto.QuestionDTO;
import com.like.weblog.weblog.expection.CustomizeErrorCode;
import com.like.weblog.weblog.expection.CustomizeException;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionService {
    @Autowired
    QuestionMap questionMap;
    @Autowired
    UserMapper userMapper;

    public PageQuestionDTO findQuestionDTO(Integer page, Integer size) {
        Integer offset = size*(page-1);
        //封装页面的数据信息
        List<Question> questions = questionMap.findAllQuestion(offset,size);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for(Question question:questions){
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = userMapper.getUserById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        //封装分页信息
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setQuestionDTOs(questionDTOs);
        //查询数据数量
        Integer totalCount = questionMap.count();
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }

    public PageQuestionDTO findQuestionDTOByCreater(Integer page, Integer size, Long creater) {
        Integer offset = size*(page-1);
        //java8的新方法
        //封装页面的数据信息
        List<Question> questions = questionMap.findQuestionByCreater(offset,size,creater);
        User user = userMapper.getUserById(creater);
        List<QuestionDTO> questionDTOs = questions.stream().map(question -> {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            questionDTO.setUser(user);
            return questionDTO;
        }).collect(Collectors.toList());

//        List<QuestionDTO> questionDTOs = new ArrayList<>();
//        for(Question question:questions){
//            QuestionDTO questionDTO = new QuestionDTO();
//            questionDTO.setQuestion(question);
//            User user = userMapper.getUserById(question.getCreator());
//            questionDTO.setUser(user);
//            questionDTOs.add(questionDTO);
//        }
        //封装分页信息
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setQuestionDTOs(questionDTOs);
        //查询数据数量
        Integer totalCount = questionMap.countByCreater(creater);
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }


    public QuestionDTO findQuestionById(Integer id){
        Question question = questionMap.findQUestionById(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FIND);
        }else {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = userMapper.getUserById(question.getCreator());
            questionDTO.setUser(user);
            return questionDTO;
        }
    }

    public int updateQuestion(String tag,String title,String description,String id,long modifideTime) {
        return questionMap.updateQuestion(tag,title,description,id,modifideTime);
    }

    public void incView(Integer id) {
        questionMap.incView(id);
    }

    public List<Question> findQuestionByTag(Integer id) {
//        查出问题的tag
        Question question = questionMap.findQUestionById(id);
        //修改tag为符合正则
        String[] tags = question.getTag().split(",");
        String tagsAgex = Arrays.stream(tags).collect(Collectors.joining("|"));
        question.setTag(tagsAgex);

//        通过问题的tag查出相关问题,
        List<Question> questions = questionMap.findQUestionByTag(question);
        return questions;
    }
}





