package reservation.demo.domain;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.tools.JavaFileObject;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ReservationController {
	
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private ReservationRepository rrepository;	
    
    @CrossOrigin
    @RequestMapping(value="/reservation", method=RequestMethod.POST)
    @ResponseBody
    //after axios.post
    public void reservation(HttpEntity<String> httpEntity) throws ParseException {
//    	System.out.println("HERE!!!!!");
//    	System.out.println(httpEntity.getBody());
    	//парсер преобразует строку в спец объект
    	JSONParser parser = new JSONParser();
    	//удобно обращаться после того как парсили
    	JSONObject json = (JSONObject) parser.parse(httpEntity.getBody());
//    	System.out.println(json);
    	System.out.println(json.get("time"));
    	Long time = (long) json.get("time");
    	Long machine = (long) json.get("machine");
    	User user = urepository.findByUsername((String) json.get("name"));
    	if (rrepository.findByTimeAndMachine(time, machine).isEmpty()) {
    		Reservation reservation = new Reservation(user, time, new java.sql.Date(1235), machine);
    		rrepository.save(reservation);
    		}
//    		return "OK";
//    	}
//    	else {
//    		return "denied";
//    	}
    }
    
	@RequestMapping(value="/machines")
	public String machines(Model model) {
		//make the names of the buttons
		int count_machines = 3;
		int count_time_zone = 3;
		for (int i = 0; i < count_time_zone; ++i) {
			for (int j = 0; j < count_machines; ++j) {
				//find and put reserved or vacant
				if(rrepository.findByTimeAndMachine(new Long(i), new Long(j)).isEmpty()) {
					model.addAttribute("temp" + new Integer(i * 3 + j).toString(), "Vacant");
				}
				else {
					model.addAttribute("temp" + new Integer(i * 3 + j).toString(), "RESERVED");
				}
			}
		}
		return "machines";
	}
}