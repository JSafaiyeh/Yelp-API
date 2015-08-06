# Yelp Search API | Java
Java API for [Yelp search](https://www.yelp.com/developers/documentation/v2/search_api). 


##Usage
Use your Api Keys for OAuth, register at  [Yelp for Developers](https://www.yelp.com/developers/manage_api_keys).
```java
  YelpApi yelpApi = new YelpApi(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
```



Use a HashMap for search paramenters outlined [here](https://www.yelp.com/developers/documentation/v2/search_api);
```java
  HashMap<String, String> parameters = new HashMap<>();
  parameters.put("term", "food");
  parameters.put("limit", "5");
  parameters.put("offset", "10");
  parameters.put("sort", "0"); // 0 = Best Matched, 1 = Distance, 2 = Highest Rated
  parameters.put("category_filter", "active"); //List of supported filters: https://www.yelp.com/developers/documentation/v2/all_category_list
  parameters.put("radius_filter", "5000"); //Max 40,000 meters
  parameters.put("deals_filter", "false"); //Exclusively show places with deals.
```



Either search with a location String or coordinates
```java
  yelpApi.searchLocation("San Jose, CA", parameters);
  yelpApi.searchCoordinates(latitude, longitude, parameters);
```
