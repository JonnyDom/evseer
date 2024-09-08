# EVSeer

App designed to monitor the solar power production and, using that data,
control your electrical vehicle's charging system. 
It integrates with [FoxESSCloud Open API](https://www.foxesscloud.com/public/i18n/en/OpenApiDocument.html#1) (solar power system)


### Necessary configuration
To deploy the app for your personal use, you need to include an "env.properties" file in the web-layer/src/main/resources folder,
where you need to put the following:
```
FOX_ESS_TOKEN=<the-generated-API-key>
DEVICE_SERIAL_NUMBER=<serial-number>
```
- the-generated-API-key is the key generated in the [Fox Ess Cloud page](https://www.foxesscloud.com/user/center), under API Management.
- The Serial Number of the inverter device