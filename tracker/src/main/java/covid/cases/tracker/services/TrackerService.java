package covid.cases.tracker.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import covid.cases.tracker.model.LocationDetails;

@Service
public class TrackerService {
    //global cases
	private static String casesUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    //demise global
//	private static String demiseUrl="https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
//    //recovred 
//	private static String recovered="https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
//    //indian vaccinated
//	private static String indian="https://github.com/owid/covid-19-data/blob/master/public/data/vaccinations/country_data/India.csv";
//	
	
	private ArrayList<LocationDetails> details=  new ArrayList<>();
	
	public ArrayList<LocationDetails> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<LocationDetails> details) {
		this.details = details;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException {
		
		ArrayList<LocationDetails> detail=  new ArrayList<>();
		
		URL url = new URL(casesUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		
		for (CSVRecord record : records) {
			LocationDetails location = new LocationDetails();
            location.setState(record.get("Province/State"));
            location.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            location.setTotalCase(latestCases);
            location.setDiffFromPrevDay(latestCases - prevDayCases);
            detail.add(location);
            //System.out.print(detail);
        }
		this.details=detail;
		in.close();
	}
}
