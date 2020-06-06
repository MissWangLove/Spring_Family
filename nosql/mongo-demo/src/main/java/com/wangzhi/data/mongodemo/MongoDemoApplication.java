package com.wangzhi.data.mongodemo;

import com.mongodb.client.result.UpdateResult;
import com.wangzhi.data.mongodemo.converter.MoneyReadConverter;
import com.wangzhi.data.mongodemo.model.Coffee;
import com.wangzhi.data.mongodemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@Slf4j
@EnableMongoRepositories
public class MongoDemoApplication implements ApplicationRunner {

	private CoffeeRepository coffeeRepository;

	@Autowired
	public void setCoffeeRepository(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;
	}

	/*private MongoTemplate mongoTemplate;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(MongoDemoApplication.class, args);
	}

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Collections.singletonList(new MoneyReadConverter()));
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 2.0))
				.createTime(new Date())
				.updateTime(new Date()).build();
		Coffee save = mongoTemplate.save(espresso);
		log.info("save {} : ", save);

		List<Coffee> coffees = mongoTemplate.find(query(Criteria.where("name").is("espresso")), Coffee.class);
		log.info("size {}", coffees.size());
		coffees.forEach(c -> log.info("c {}", c));

		Thread.sleep(1000);

		UpdateResult updateResult = mongoTemplate.updateFirst(query(Criteria.where("name").is("espresso")),
				new Update().set("price", Money.of(CurrencyUnit.of("CNY"), 30)).currentDate("updateTime"), Coffee.class);
		log.info("update Result : {}", updateResult.getModifiedCount());
		Coffee byId = mongoTemplate.findById(save.getId(), Coffee.class);
		log.info("Update result : {}", byId);

		mongoTemplate.remove(byId);*/

		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date()).build();
		Coffee latte = Coffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.createTime(new Date())
				.updateTime(new Date()).build();

		coffeeRepository.insert(Arrays.asList(espresso, latte));
		coffeeRepository.findAll(Sort.by("name"))
				.forEach(c -> log.info("Saved Coffee {}", c));

		Thread.sleep(1000);
		latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
		latte.setUpdateTime(new Date());
		coffeeRepository.save(latte);
		coffeeRepository.findByName("latte")
				.forEach(c -> log.info("Coffee {}", c));

		coffeeRepository.deleteAll();
	}
}
