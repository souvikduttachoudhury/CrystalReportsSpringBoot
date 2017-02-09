package com.lister.Project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.lister.Project.controller,com.lister.Project.domain,com.lister.Project.service,com.lister.Project.dao"})
@ConfigurationProperties   //Adding the dependency is creating conflict
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	ApplicationContext context;
	
	@Bean
	public ErrorPageFilter errorPageFilter() {
	    return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {  //Disabling error filter to get the full stack trace
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    filterRegistrationBean.setFilter(filter);
	    filterRegistrationBean.setEnabled(false);
	    return filterRegistrationBean;
	}
	
	@Bean
	  public ViewResolver viewResolver() {  // Thymeleaf View Resolver to resolve views
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	    resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding("UTF-8");
	    return resolver;
	  }

	
	  @Bean
	  public TemplateEngine templateEngine() { // To enable 'th:' tags of thymeleaf
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setEnableSpringELCompiler(true);
	    engine.setTemplateResolver(templateResolver());
	    return engine;
	  }

	  /**
	 * @return
	 */
	private ITemplateResolver templateResolver() {   // Thymeleaf Template Resolver to resolve templates
	    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	    resolver.setApplicationContext(context);
	    resolver.setPrefix("/resources/templates/");
	    resolver.setSuffix(".html");
	    resolver.setTemplateMode(TemplateMode.HTML);
	    return resolver;
	  }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { //Registering Resource locations
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		if (!registry.hasMappingForPattern("/jsp/**")) {
			registry.addResourceHandler("/jsp/**").addResourceLocations("classpath:/WEB-INF/jsp/");
		}
	 }
	
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/save").setViewName("employeedtls");
        registry.addViewController("/generate").setViewName("reportlist");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
    }    
}
