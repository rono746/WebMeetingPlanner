package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.*;
import com.example.WebMeetingPlanner.Repository.*;
import com.example.WebMeetingPlanner.Service.CustomUserDetails;
import com.example.WebMeetingPlanner.Service.DatesService;
import com.example.WebMeetingPlanner.Service.MeetingService;
import com.example.WebMeetingPlanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class SchedulingController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingController.class);
//	BasicConfigurator.configure();
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private DatesService datesService;

	@Autowired
	private UserService userService;

	@Autowired
	private MeetingsRepository meetingsRepository;

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomsRepository roomsRepository;

	@Autowired
	private OrganisationRepository organisationRepository;



	@GetMapping("/Scheduling")
	public String SchedulingMeeting(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
		List<Organization> listorganisation = organisationRepository.findAll();
		model.addAttribute("listorganisation", listorganisation);

		String email = loggedUser.getUsername();
		User user = userService.getByEmail(email);

		long Organisation_id = user.getOrganization().getOrganisation_id();


		List<TracomRooms> listrooms = meetingService.getRoomsOrganization(Organisation_id);
		List<User> users = userService.getUsersByOrganisation(Organisation_id);

//		List<User> users1 = meetingsRepository.findByUserId(1);

//		model.addAttribute("users1", users1);
		model.addAttribute("room", new TracomRooms());

		model.addAttribute("listsrooms", listrooms);
		model.addAttribute("listusers", users);

		model.addAttribute("meetingschedules", new Scheduling());

		return "schedule";
	}


	/**get conflicting meeting**/
	@PostMapping("/meetingSaveSuccess")
	public ModelAndView MeetingSave(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails loggedUser, @ModelAttribute("meets") Scheduling scheduling, ModelAndView modelandview, Model model, BindingResult bindingResult)
	{
		String mail = loggedUser.getUsername();
		User user =  userService.getByEmail(mail);
		scheduling.setOrganization(user.getOrganization());


		long Organisation_id = user.getOrganization().getOrganisation_id();

		LocalDateTime date1 = LocalDateTime.now();
		LocalDateTime date2 = LocalDateTime.now();
//		LocalDateTime date3 = LocalDateTime.ge;

//		List<Scheduling> schedules = meetingsRepository.findMeetingsByDate(Organisation_id,date1,date2);

		List<Scheduling> dates1 = meetingService.getOrganizationMeetings(Organisation_id);

//		});
		Scheduling conflict = meetingsRepository.findConflictingMeeting(scheduling.getTracomRooms(), scheduling.getStartDate());
		if( conflict != null || bindingResult.hasErrors()){


			modelandview.setViewName("meetingSavedFailure");

		}else{
//			scheduling.setDescription("Meeting is conflicting with an existing meeting");
//			bindingResult.rejectValue("startDate", "DateExist");
			meetingsRepository.save(scheduling);
			modelandview.setViewName("meetingSavedSuccess");

		}

		return modelandview;
	}
	/**conflicting meeings edition ends here**/

	@GetMapping("/SaveMeeting")
	public String Scheduling(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		String mail = loggedUser.getUsername();

		User user =  userService.getByEmail(mail);

		long Organisation_id = user.getOrganization().getOrganisation_id();

		List<Scheduling> schedules = meetingService.getOrganizationMeetings(Organisation_id);

        List<Scheduling> listMeeting = meetingsRepository.findAll();


		LocalDateTime today = LocalDateTime.now();
		List<Scheduling> schedule = meetingsRepository.findMeetingByDateTime(Organisation_id);

		schedule.forEach(m -> {
			LocalDateTime dates = m.getStartDate();

			/** current time*/
			LocalDateTime now = LocalDateTime.now();

			System.out.println(now);

			/** current datetime minus 15 minutes **/
			LocalDateTime tobe = now.minus(15, ChronoUnit.MINUTES);


		});
		model.addAttribute("listMeeting", schedules);

		model.addAttribute("meetingschedules", new Scheduling());
		return "ScheduledMeeting";
	}

	@GetMapping("/PastSaveMeeting")
	public String PastScheduling(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {

		String mail = loggedUser.getUsername();

		User user =  userService.getByEmail(mail);

		long Organisation_id = user.getOrganization().getOrganisation_id();


		List<Scheduling> PastSchedules = meetingsRepository.findPastMeetingsByTime(Organisation_id);
		List<Scheduling> listMeeting = meetingsRepository.findAll();



		model.addAttribute("PastListMeeting", PastSchedules);
		model.addAttribute("meetingschedules", new Scheduling());
		return "PastScheduledMeeting";
	}



}

