package com.hongying.service.impl;

import com.hongying.enums.RelationEnum;
import com.hongying.enums.TypeEnum;
import com.hongying.repository.domain.EntityCategory;
import com.hongying.repository.mapper.EntityCategoryDAO;
import com.hongying.service.AnnotationService;
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
    ApplicationContext applicationContext;

    @Override
    @Transactional
    public boolean initData() {
        Resource resource = new ClassPathResource("com/hongying/service/orgentity.properties");
        File file = null;
        try {
            file = resource.getFile();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            do{
                String lineStr = br.readLine();
                String[] data = lineStr.split("\t");
                EntityCategory entityCategory = new EntityCategory();
                entityCategory.setEntity(data[0]);
                entityCategory.setCategory(data[1]);
                entityCategory.setWikiUrl(data[2]);
                entityCategory.setRelation(RelationEnum.INSTANCE.getCode());
                entityCategory.setType(TypeEnum.SINGLE_RELATION.getCode());
                entityCategoryDAO.insertSelective(entityCategory);
            }while(br.read()!=-1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
