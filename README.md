This Project contains the backend of the assessment.

It contains JPA Entities for each table of the schema, four Repositories (for the countries, the regions, the country stats and country languages), a controller and a service to prepare the responses of the controller

The controller implements the following REST GET APIs:

Task1 a
<host>:<port>/getCountries

Task1 b
<host>:<port>/getCountryLanguages/{country_id}

Task2
<host>:<port>/getCountriesRecords

Task3
<host>:<port>/getCountriesStats?continent={continent_name}&region={region_name}&country={country}&fromYear={fromYear}&toYear={toYear}&orderColumn={order_column}&order={order}(
continent: (not required) If this parameter is null then no filter for the Continent is applied
region: (not required)  If this parameter is null then no filter for the Region is applied
country: (not required) If this parameter is null then no filter for the Country is applied
fromYear: (not required)(default value 0) If this parameter is null then there is no start date as a filter
toYear: (not required)(default value 9999) If this parameter is null then there is no end date as a filter
orderColumn: (not required)(default value CONTINENT) this parameter takes as value one of the following (CONTINENT, REGION, CONTINENT) and desides for which column the ordering is performed
order: (not required)(default value ASCENDING) this parameter takes as value one of the following (ASCENDING, DESCENDING) and desides the ordering
