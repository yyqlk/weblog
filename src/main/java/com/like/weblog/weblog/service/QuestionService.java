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

@Service
public class QuestionService {
    @Autowired
    QuestionMap questionMap;
    @Autowired
    UserMapper userMapper;

    /**
     * 查询全部的questiondto，包含分页信息和用户信息
     * @param page
     * @param size
     * @return
     */
    public PageQuestionDTO findPageQuestionDTO(Integer page, Integer size) {
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
        //设置分页
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }

    /**
     * 根据search信息搜索相关的questiondto
     * @param page
     * @param size
     * @param search
     * @return
     */
    public PageQuestionDTO findPageQuestionDTO(Integer page, Integer size, String search) {
        Integer offset = size*(page-1);
        //java8的新方法
        //封装页面的数据信息
        List<Question> questions = questionMap.findQuestionBySearch(offset,size,search);
        List<QuestionDTO> questionDTOs = questions.stream().map(question -> {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = userMapper.getUserById(question.getCreator());
            questionDTO.setUser(user);
            return questionDTO;
        }).collect(Collectors.toList());

        //封装分页信息
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setQuestionDTOs(questionDTOs);
        //查询数据数量
        Integer totalCount = questionMap.countBysearch(search);
        //设置分页
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }

    /**
     * 根据creater查询questiondto
     * @param page
     * @param size
     * @param creater
     * @return
     */
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
/*
两种方法效果一样
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for(Question question:questions){
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = userMapper.getUserById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
 */

        //封装分页信息
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setQuestionDTOs(questionDTOs);
        //查询数据数量
        Integer totalCount = questionMap.countByCreater(creater);
        //设置分页
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }


    /**
     * 根据tag查询相关问题
     * @param id
     * @return
     */
    public List<Question> findQuestionByTag(Integer id) {
        //查出问题的tag
        Question question = questionMap.findQUestionById(id);
        //修改tag为符合正则
        String[] tags = question.getTag().split(",");
        String tagsAgex = Arrays.stream(tags).collect(Collectors.joining("|"));
        question.setTag(tagsAgex);

        //通过问题的tag查出相关问题,
        List<Question> questions = questionMap.findQUestionByTag(question);
        return questions;
    }

    /**
     * 根据id查询questionDTO
     * @param id
     * @return
     */
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


    /**
     * 更新question
     * @param tag
     * @param title
     * @param description
     * @param id
     * @param modifideTime
     * @return
     */
    public int updateQuestion(String tag,String title,String description,String id,long modifideTime) {
        return questionMap.updateQuestion(tag,title,description,id,modifideTime);
    }

    /**
     * 增加阅读数
     * @param id
     */
    public void incView(Integer id) {
        questionMap.incView(id);
    }

}





