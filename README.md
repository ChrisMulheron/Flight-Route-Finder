# Flight-Route-Finder

# Example of program flow:

*************   PART A   *************

Flight                        Cost                
Edinburgh->Heathrow        £110.0
Heathrow->Edinburgh        £110.0
Heathrow->Amsterdam        £100.0
Amsterdam->Heathrow        £100.0
Heathrow->Boston        £230.0
Boston->Heathrow        £230.0
Boston->Chicago        £150.0
Chicago->Boston        £150.0
Boston->Montreal        £100.0
Montreal->Boston        £100.0
Montreal->Toronto         £90.0
Toronto->Montreal         £90.0
Edinburgh->Chicago        £560.0
Chicago->Edinburgh        £560.0
New Delhi->Shanghai        £430.0
Shanghai->New Delhi        £430.0
Shanghai->Hong Kong        £230.0
Hong Kong->Shanghai        £230.0

*************   PART B/C   *************

Enter the location you're departing from (or type 'exit' to terminate the program): 
Edinburgh
Departure: Edinburgh

Enter the location to travel to (or type 'exit' to terminate the program): 
Chicago
Destination: Chicago

*************   PART D  *************

Printing itinerary for return flight

       Leg      Departure        At             On    Destination             At       Duration           Cost
         1      Edinburgh     10:00            EH1       Heathrow          11:20            120           110
         2       Heathrow     11:00            HB1         Boston          17:20            620           230
         3         Boston     13:00            BC1        Chicago          16:00            300           150

Total cost of shortest single flight: £490


*************   PART F  *************

If you have a voucher, please enter the code, otherwise enter no.
no

Would you like a return flight? yes or no.
yes

Printing itinerary for return flight


       Leg      Departure        At             On    Destination             At       Duration           Cost
         4        Chicago     14:00            CB1         Boston          17:00            300           150
         5         Boston     10:00            BH1       Heathrow          16:20            620           230
         6       Heathrow     12:00            HE1      Edinburgh          13:20            120           110

Cost of return journey: £980


Terminating program