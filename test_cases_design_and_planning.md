## Story
As a user, I can search the route between Madrid (Spain) and Barcelona (Spain), so I get information for trips by train.

## Scenario
Valid city names and departure/return dates return results.

## Rules
Search returns results when the cities are part of the system's coverage and the dates are in the future, also the 
return date must be the same or after the departure date.

## Test Cases

### There is at least one result (Smoke test)
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on the Smartest/Cheapest/Fastest/Departure time/Arrival time tab and then on the Train tab

**Then** At least one result is present on each one of the Smartest/Cheapest/Fastest/Departure/Arrival tabs.
 
_Note_ This test could be split for every category tab (Smartest/Cheapest...), but for the sake of simplicity I placed 
them all in this one.

### Results are ordered by price on the Cheapest tab
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on the Cheapest tab and afterwards on the Train tab

**Then** I see all the results ordered by price (ascendant), length (shorter first), and departure (earlier first)

### Results are ordered by price on the Fastest tab
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on the Fastest tab and afterwards on the Train tab

**Then** I see all the results ordered by length (shorter first), price (ascendant), and departure time (earlier first) 

### Results are ordered by price on the Departure time tab
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on the Departure time tab and afterwards on the Train tab

**Then** I see all the results ordered by departure time (earlier first), price (ascendant), length (shorter first)

### Results are ordered by price on the Arrival time tab
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on the Arrival time tab and afterwards on the Train tab

**Then** I see all the results ordered by arrival time (earlier first), price (ascendant), length (shorter first)

### First result of selected tab (Smartest/Cheapest/Fastest/Departure/Arrival) coincides with results in header area
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on the (Smartest/Cheapest/Fastest/Departure/Arrival) time tab and afterwards on the Train tab

**Then** the first result matches the result shown above the (Smartest/Cheapest/Fastest/Departure/Arrival) tabs 

### Trip length matches departure and arrival time
**Given** A search for the route between Madrid (Spain) and Barcelona (Spain) was executed

**When** I click on any of the (Smartest/Cheapest/Fastest/Departure/Arrival) time tab and afterwards on the Train tab

**Then** the trip length coincides with the arrival minus the departure time 

