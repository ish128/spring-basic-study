package hello.core.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

  @Test
  void filterScan1(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    BeanA beanA = ac.getBean(BeanA.class);
    assertThat(beanA).isInstanceOf(BeanA.class);
  }

  @Test
  void noFilterScan2(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean(BeanB.class));
  }

  @Test
  void filterScan3(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    BeanC beanC = ac.getBean(BeanC.class);
    assertThat(beanC).isInstanceOf(BeanC.class);
  }

  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)
    , excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
  static class ComponentFilterAppConfig{

  }
}
