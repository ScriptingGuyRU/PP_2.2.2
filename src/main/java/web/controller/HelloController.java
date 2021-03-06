package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.config.WebConfig;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

	@Autowired
	private CarService carService;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello"; //Тут имя jsp
	}

	@RequestMapping(value = "car", method = RequestMethod.POST)
	public String newController(@RequestParam String locale, ModelMap modelMap) {
    	modelMap.addAttribute("localeCar",setLocale(locale));
        List<Car> cars = carService.getSomeCars();
        modelMap.addAttribute("cars",cars);
        return "car";
	}

	public String setLocale(String locale) {
		if (locale.equals("en")) {
			return "CARS";
		} else if (locale.equals("ru")) {
			return "МАШИНЫ";
		} else {
			return "EXCEPTION, uncorrect location value";
		}
	}
	
}