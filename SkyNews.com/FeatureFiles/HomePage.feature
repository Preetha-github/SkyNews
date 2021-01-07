@Regression @Homepage 
Feature: This feature will test SkyNews.com homepage - Title, Categories and story 

Background:  
Given User navigates to SkyNews homepage
When User clicks 'Accept' cookies

Scenario: Verify Browser tab's title
Then Page title should be displayed

Scenario: Verify number of categories displayed and their names
Then Number of categories should be displayed along with their names.

Scenario: Verify default focus is on Home Category
Then Home category should be selected by default

@Story
Scenario: Verify that is the user clicks 'Climate' this becomes the focus
When User clicks 'Climate' category
Then Climate should be focussed

@Story
Scenario: Select a story from homepage. Verify that a word of your choice in the title text of the article you have just selected appears once title of the new page loads
When User clicks a story from homepage
Then Title text of the article selected should appear in the new page