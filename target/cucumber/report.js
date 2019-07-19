$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("classpath:com/test/BBBY_Rest_api/features_api/CarrierChildServiceDelivery.feature");
formatter.feature({
  "name": "Create Orders",
  "description": "  I want to create bulk orders",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@test"
    }
  ]
});
formatter.scenario({
  "name": "Create Orders",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@test"
    },
    {
      "name": "@test"
    }
  ]
});
formatter.step({
  "name": "I create test Data for Carrier Schedule",
  "rows": [
    {
      "cells": [
        "Carrier",
        "nonWorking",
        "Service",
        "Reason",
        "StartDate",
        "EndDate"
      ]
    },
    {
      "cells": [
        "UPS",
        "2020-11-11",
        "UPS GROUND",
        "HOLIDAY",
        "2020-11-10",
        "2020-11-11"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I fetch Facility Delivery Schedule with below Request",
  "rows": [
    {
      "cells": [
        "Carrier",
        "Date",
        "NumberofDays",
        "ServiceLevel"
      ]
    },
    {
      "cells": [
        "UPS",
        "2020-11-10",
        "2",
        "UPS GROUND"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I get the below Carrier Delivery Schedule",
  "rows": [
    {
      "cells": [
        "Carrier",
        "Date",
        "ServiceLevel",
        "Available",
        "Reason_Code",
        "Code_Desc"
      ]
    },
    {
      "cells": [
        "UPS",
        "2020-11-10",
        "UPS GROUND",
        "Y",
        "",
        ""
      ]
    },
    {
      "cells": [
        "UPS",
        "2020-11-11",
        "UPS GROUND",
        "Y",
        "",
        ""
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});