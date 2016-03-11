package br.com.awServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Main
 * 
 * @author rcicero
 * @email rafacicero.silva@gmail.com
 *
 */
@Configuration
@ComponentScan
@SpringBootApplication
public class Application 
{
    public static void main( String[] args ){
      SpringApplication.run(Application.class, args);
    }
}
