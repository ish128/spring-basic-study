package hello.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContainerTest {

  @Test
  @DisplayName("스프링컨테이너에 있는 모든 빈 출력하기.")
  void findAllBeans(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for(String beanName : beanDefinitionNames){
      System.out.println("name:" + beanName + ", object:"+ ac.getBean(beanName));
    }
  }

  @Test
  @DisplayName("스프링컨테이너에 있는 모든 어플리케이션 빈 출력하기.")
  void findApplicationBeans(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for(String beanName : beanDefinitionNames){
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
      if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
        System.out.println("name:" + beanName + ", object:"+ ac.getBean(beanName));
      }
    }
  }
}
