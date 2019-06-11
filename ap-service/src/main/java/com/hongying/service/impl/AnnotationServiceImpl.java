package com.hongying.service.impl;

import com.hongying.enums.RelationEnum;
import com.hongying.enums.TypeEnum;
import com.hongying.exceptions.ApException;
import com.hongying.repository.domain.EntityCategory;
import com.hongying.repository.domain.UserFeedback;
import com.hongying.repository.mapper.EntityCategoryDAO;
import com.hongying.repository.mapper.UserFeedbackDAO;
import com.hongying.service.AnnotationService;
import com.hongying.service.builder.UserFeedbackBuilder;
import com.hongying.service.dto.EntityCategoryDTO;
import com.hongying.service.dto.UserFeedbackDTO;
import com.hongying.service.request.AddFeedbackRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

@Service
public class AnnotationServiceImpl implements AnnotationService {
    @Autowired
    private EntityCategoryDAO entityCategoryDAO;

    @Autowired
    private UserFeedbackDAO userFeedbackDAO;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    @Transactional
    public boolean initData() {
        Resource resource = new ClassPathResource("com/hongying/service/orgentity.properties");
        File file = null;
        try {
            file = resource.getFile();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineStr = br.readLine();
            while (lineStr != null) {
                String[] data = lineStr.split("\t");
                EntityCategory entityCategory = new EntityCategory();
                entityCategory.setEntity(data[0]);
                entityCategory.setCategory(data[1]);
                entityCategory.setWikiUrl(data[2]);
                entityCategory.setRelation(RelationEnum.INSTANCE.getCode());
                entityCategory.setType(TypeEnum.SINGLE_RELATION.getCode());
                entityCategoryDAO.insertSelective(entityCategory);
                lineStr = br.readLine();
            }
            return true;
        } catch (IOException e) {
           throw new ApException(e.getMessage());
        }
    }

    @Override
    public EntityCategoryDTO getNextEntityCategory(Long userId, Integer type) {
        Long minId = 0L;
        //query last record in userFeedback DB
        UserFeedback userFeedback = userFeedbackDAO.selectLastRecordByUserIdAndType(userId, type);
        if (userFeedback != null) {
            minId = userFeedback.getEntityCategoryId();
        }
        EntityCategory entityCategory = entityCategoryDAO.selectOneByMinIdAndType(minId, type);
        if (entityCategory == null) {
            return null;
        }
        EntityCategoryDTO entityCategoryDTO = new EntityCategoryDTO();
        BeanUtils.copyProperties(entityCategory, entityCategoryDTO);
        return entityCategoryDTO;
    }

    @Override
    @Transactional
    public void feedback(Long userId, AddFeedbackRequest request) {
        Integer type = request.getUserFeedbacks().get(0).getEntityCategoryType();
        //query last record in userFeedback DB
        EntityCategoryDTO entityCategoryDTO = getNextEntityCategory(userId, type);
        if (entityCategoryDTO == null) {
            throw new ApException("all record already feedback");
        }
        if (!request.getUserFeedbacks().get(0).getEntityCategoryId().equals(entityCategoryDTO.getId())) {
            throw new ApException("miss or repeat feedback,please check again");
        }

        for (UserFeedbackDTO userFeedbackDTO : request.getUserFeedbacks()) {
            UserFeedback userFeedback = UserFeedbackBuilder.build(userId, type, userFeedbackDTO);
            userFeedbackDAO.insertSelective(userFeedback);
        }
    }


}
