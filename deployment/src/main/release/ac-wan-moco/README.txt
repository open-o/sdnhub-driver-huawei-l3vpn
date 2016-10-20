Introduction
============

This guide helps to setup the moco server for AC-WAN Controller to learn
the REST API supported by l3-vpn driver service.

Configuration of the Mocked AC-WAN Controller environment
=========================================================

1. Install the moco standalone server by following the steps given at https://github.com/dreamhead/moco/blob/master/moco-doc/usage.md#shell

2. Run the moco server using following command to mock the AC-WAN Controller:

             java -jar moco-runner-0.11.0-standalone.jar https -p 18008 -c acwancontroller.json -https cert.jks -cert mocohttps --keystore mocohttps

    acwancontroller.json file can be found in sample-api-usage: https://gerrit.open-o.org/r/#/c/4093/
    cert.jks can be downloaded from here : https://github.com/dreamhead/moco/tree/master/moco-runner/src/test/resources/certificate

    Please note down the shutdown port reported in console when moco server started. And use that port to shutdown the server as below:

         moco shutdown -p <shutdown port number>

    Now controller is up. Following is the example to access the api:

    https://<Simulated Conroller IP>:18008/restconf/config/huawei-ac-net-l3vpn:l3vpn-cfg/instances/instance/123
    Method: GET
    HEADER: Content-Type: application/json

    Response:
    Status code: 200
    Body:
        <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <l3vpn-cfg> <instances> <instance> <id>00000000-0000-0000-0000-000000000001</id> <name>netl3vpn8</name> <user-label>858</user-label> <admin-status>adminDown</admin-status> <mtu>1500</mtu> <label-apply-mode>855</label-apply-mode> <frr>false</frr> <acs> <ac> <id>00000000-0000-0000-0001-000000000001</id> <ne-id>04378ca31a0d4a66b7ff2356bcf3dc58</ne-id> <user-label>858</user-label> <l2-access> <access-type>dot1q</access-type> <port> <ltp-id>04378ca31a0d4a66b7ff2356bcf3dc28</ltp-id> </port> <dot1q> <ltp-id>04378ca31a0d4a66b7ff2356bcf3dc28</ltp-id> <vlan-id>22</vlan-id> </dot1q> <vxlan> <vni-id>241</vni-id> </vxlan> </l2-access> <l3-access> <address>192.164.1.1/24</address> <static-routes> <static-route> <ip-prefix>192.16.1.1/24</ip-prefix> <next-hop>192.16.1.121</next-hop> <preference>859</preference> <description>description</description> <track-bfd-enable>enable</track-bfd-enable> </static-route> <static-route> <ip-prefix>192.16.1.1/24</ip-prefix> <next-hop>192.16.1.121</next-hop> <preference>859</preference> <description>description</description> <track-bfd-enable>enable</track-bfd-enable> </static-route> </static-routes> </l3-access> </ac> <ac> <id>00000000-0000-0000-0001-000000000002</id> <ne-id>04378ca31a0d4a66b7ff2356bcf3dc13</ne-id> <user-label>858</user-label> <l2-access> <access-type>dot1q</access-type> <port> <ltp-id>04378ca31a0d4a66b7ff2356bcf3dc09</ltp-id> </port> <dot1q> <ltp-id>04378ca31a0d4a66b7ff2356bcf3dc09</ltp-id> <vlan-id>22</vlan-id> </dot1q> <vxlan> <vni-id>241</vni-id> </vxlan> </l2-access> <l3-access> <address>192.168.224.2/24</address> <static-routes> <static-route> <ip-prefix>192.16.1.1/24</ip-prefix> <next-hop>192.16.1.121</next-hop> <preference>859</preference> <description>description</description> <track-bfd-enable>enable</track-bfd-enable> </static-route> <static-route> <ip-prefix>192.16.1.1/24</ip-prefix> <next-hop>192.16.1.121</next-hop> <preference>859</preference> <description>description</description> <track-bfd-enable>enable</track-bfd-enable> </static-route> </static-routes> </l3-access> </ac> <ac> <id>00000000-0000-0000-0001-000000000003</id> <ne-id>04378ca31a0d4a66b7ff2356bcf3dcea</ne-id> <user-label>858</user-label> <l2-access> <access-type>dot1q</access-type> <port> <ltp-id>04378ca31a0d4a66b7ff2356bcf3dcea</ltp-id> </port> <dot1q> <ltp-id>04378ca31a0d4a66b7ff2356bcf3dcea</ltp-id> <vlan-id>22</vlan-id> </dot1q> <vxlan> <vni-id>241</vni-id> </vxlan> </l2-access> <l3-access> <address>192.168.54.2/24</address> <static-routes> <static-route> <ip-prefix>192.16.1.1/24</ip-prefix> <next-hop>192.16.1.121</next-hop> <preference>859</preference> <description>description</description> <track-bfd-enable>enable</track-bfd-enable> </static-route> <static-route> <ip-prefix>192.16.1.1/24</ip-prefix> <next-hop>192.16.1.121</next-hop> <preference>859</preference> <description>description</description> <track-bfd-enable>enable</track-bfd-enable> </static-route> </static-routes> </l3-access> </ac> </acs> <topo-service> <hub-spoke> <hub-group> <hub-ac> <ac-id>00000000-0000-0000-0001-000000000001</ac-id> <hub-direction>IN</hub-direction> </hub-ac> </hub-group> <spoke-group> <local-bridge>false</local-bridge> <spoke-ac> <acId>00000000-0000-0000-0001-000000000002</acId> </spoke-ac> <spoke-ac> <acId>00000000-0000-0000-0001-000000000003</acId> </spoke-ac> </spoke-group> </hub-spoke> </topo-service> <ac-protect-groups> <backAcId>00000000-0000-0000-0001-000000000002</backAcId> <masterAcId>00000000-0000-0000-0001-000000000001</masterAcId> <vrrp> <virtual-ip>10.10.10.20</virtual-ip> </vrrp> </ac-protect-groups> <diff-serv> <color>yellow</color> </diff-serv> <tunnel-service> <type>auto-select</type> </tunnel-service> </instance> </instances> </l3vpn-cfg>

    3. Add AC-WAN to ESR service by using below sample json (please refer http://wiki.open-o.org , on how to register AC-WAN controller using ESR)

        Request:

            http://192.168.4.39:80/api/extsys/v1/sdncontrollers
            Header:
                Method: Post
                Content-Type: application/json

            Body:
                {
                  "name": <controller name>,
                  "vendor": <vendor name>,
                  "version": <version>,
                  "description": <description of controller>,
                  "type": <type of controller>,
                  "createTime": <create time>,
                  "category": <category of controller>,
                  "url": <url of controller e.g. https://192.168.2.228:18008>,
                  "userName": <username of controller e.g. admin>,
                  "password": <password of controller e.g. admin>,
                  "productName": <product name of controller>,
                  "protocol": <supported protocol e.g. restconf>
                }

        Response
            Status Code: 201
            Body:
                {
                  "name": <controller name>,
                  "vendor": <vendor name>,
                  "version": <version>,
                  "description": <description of controller>,
                  "type": <type of controller>,
                  "createTime": "2016-10-20 07:06:37",
                  "sdnControllerId": <Controller ID to be used in NBI to create l3vpn e.g. "aff3b20d-66f5-4188-adcf-9fb5613f98cf">,
                  "url": <url of controller>,
                  "userName": <username of controller>,
                  "password": <password of controller>,
                  "productName": <product name of controller>,
                  "protocol": <supported protocol>
                }

 4. For each REST API supported by l3-vpn driver services, corresponding samples request and response details are provided under the folder 'sample-api-usage'.

Ensure that 'driver.json' file under folder 'generalconfig/driver.json' is configured with correct IP address for l3-vpn driver. This information will be used for
driver registeration with driver manager.

Tutorial
=========

To test the REST API, either postman or curl command could be used. Before testing,
update the header "X-Driver-Parameter": "extSysID=<Conroller ID generated by ESR>" and
use the JSON body as it is.

Bringup the docker container for MSB, ESR, Driver-Manager and l3-vpn driver service as per wiki (https://wiki.open-o.org/view/Installation_Instructions).
Bringup the mocked/simuated controller as per the previous section.

NBI to create l3vpn

http://<l3vpn driver IP>:8533/openoapi/sbi-l3vpn/v1/l3vpns
HEADER:
 Content-Type: application/json
 X-Driver-Parameter: extSysID=aff3b20d-66f5-4188-adcf-9fb5613f98cf(controller id from the previous rest query. This is identifier for controller in ESR.)
Body:

{
    "id": "00000000-0000-0000-0000-000000000001",
    "name": "netl3vpn8",
    "description": "test",
    "mtu": 1500,
    "adminStatus": "adminDown",
    "operStatus": "operateUp",
    "topology": "hubSpoke",
    "frr": false,
    "diffServ": {
        "mode": "uniform",
        "serviceClass": "BE",
        "color": "yellow"
    },
    "topologyService": {
        "hubGroups": {
            "hubGroup": [{
                "acId": "00000000-0000-0000-0001-000000000001",
                "hubDirection": "IN"
            }]
        },
        "spokeGroup": {
            "localBridge": false,
            "spokeAc": [{
                "acId": "00000000-0000-0000-0001-000000000002"
            }, {
                "acId": "00000000-0000-0000-0001-000000000003"
            }]
        }
    },
    "acs": {
        "ac": [{
            "id": "00000000-0000-0000-0001-000000000001",
            "name": "port1",
            "localName": "aacc",
            "neId": "04378ca31a0d4a66b7ff2356bcf3dc58",
            "ltpId": "04378ca31a0d4a66b7ff2356bcf3dc28",
            "adminStatus": "adminUp",
            "operStatus": "operateUp",
            "description": "aacc",
            "tenantId": "04378ca31a0d4a66b7ff2356bcf3dcea",
            "l2Access": {
                "accessType": "dot1q",
                "dot1qVlanBitmap": "22",
                "qinqSvlanBitmap": null,
                "qinqCvlanBitmap": null,
                "accessAction": "KEEP",
                "pushVlanId": 241,
                "swapVlanId": 210
            },
            "l3Access": {
                "ipv4Address": "192.164.1.1/24",
                "routes": {
                    "route": [{
                        "type": "bgp",
                        "bgpRoutes": {
                            "bgpRoute": [{
                                "remoteAs": "weq",
                                "localAs": 32,
                                "peerIp": "13.1.1.1",
                                "localIp": "10.2.2.1/24",
                                "keepaliveTime": 213,
                                "holdTime": 35,
                                "password": "23asdq",
                                "advertiseCommunity": false,
                                "advertiseExtCommunity": true
                            }, {
                                "remoteAs": "sxca",
                                "localAs": 32,
                                "peerIp": "13.1.1.1",
                                "localIp": "10.2.2.1/24",
                                "keepaliveTime": 213,
                                "holdTime": 35,
                                "password": "23asdq",
                                "advertiseCommunity": false,
                                "advertiseExtCommunity": false
                            }]
                        }
                    }, {
                        "type": "ospf",
                        "ospfRoutes": {
                            "ospfRoute": [{
                                "id": "weq",
                                "networks": {
                                    "network": [{
                                        "ip-prefix": "10.10.10.10/24"
                                    }]
                                }
                            }, {
                                "id": "weq",
                                "networks": {
                                    "network": [{
                                        "ip-prefix": "10.10.10.10/24"
                                    }]
                                }
                            }]
                        }
                    }, {
                        "type": "static",
                        "staticRoutes": {
                            "staticRoute": [{
                                "ipPrefix": "192.16.1.1/24",
                                "nextHop": "192.16.1.121"
                            }, {
                                "ipPrefix": "192.16.1.1/24",
                                "nextHop": "192.16.1.121"
                            }]
                        }
                    }, {
                        "type": "isis",
                        "isisRoute": {
                            "networkEntity": "sasd"
                        }
                    }]
                }
            },
            "inboundQosPolicyId": "04378ca31a0d4a66b7ff2356bcf3dc01",
            "outboundQosPolicyId": "04378ca31a0d4a66b7ff2356bcf3dc11",
            "inboundQueuePolicyId": "04378ca31a0d4a66b7ff2356bcf3dc22",
            "outboundQueuePolicyId": "04378ca31a0d4a66b7ff2356bcf3dc33",
            "upstreamBandwidth": {
                "enable": true,
                "cir": 400,
                "pir": 400,
                "cbs": 400,
                "pbs": 400
            },
            "downstreamBandwidth": {
                "enable": true,
                "cir": 200,
                "pir": 200,
                "cbs": 200,
                "pbs": 200
            },
            "overrideFlows": {
                "overrideFlow": [{
                    "externalFlowId": "04378ca31a0d4a66b7ff2356bcf3dcea",
                    "name": "vvdd",
                    "direction": "ingress",
                    "car": {
                        "enable": true,
                        "cir": 60,
                        "pir": 60,
                        "cbs": 60,
                        "pbs": 60
                    }
                }, {
                    "externalFlowId": "04378ca31a0d4a66b7ff2356bcf3dc12",
                    "name": "aass",
                    "direction": "egress",
                    "car": {
                        "enable": true,
                        "cir": 60,
                        "pir": 60,
                        "cbs": 60,
                        "pbs": 60
                    }
                }]
            }
        }, {
            "id": "00000000-0000-0000-0001-000000000002",
            "name": "port2",
            "localName": "ssaa",
            "neId": "04378ca31a0d4a66b7ff2356bcf3dc13",
            "ltpId": "04378ca31a0d4a66b7ff2356bcf3dc09",
            "adminStatus": "adminUp",
            "operStatus": "operateUp",
            "description": "eewww",
            "tenantId": "04378ca31a0d4a66b7ff2356bcf3dcea",
            "l2Access": {
                "accessType": "dot1q",
                "dot1qVlanBitmap": "22",
                "qinqSvlanBitmap": null,
                "qinqCvlanBitmap": null,
                "accessAction": "KEEP",
                "pushVlanId": 241,
                "swapVlanId": 210
            },
            "l3Access": {
                "ipv4Address": "192.168.224.2/24",
                "routes": {
                    "route": [{
                        "type": "bgp",
                        "bgpRoutes": {
                            "bgpRoute": [{
                                "remoteAs": "weq",
                                "localAs": 32,
                                "peerIp": "13.1.1.1",
                                "localIp": "10.2.2.1/24",
                                "keepaliveTime": 213,
                                "holdTime": 35,
                                "password": "23asdq",
                                "advertiseCommunity": false,
                                "advertiseExtCommunity": false
                            }, {
                                "remoteAs": "sxca",
                                "localAs": 32,
                                "peerIp": "13.1.1.1",
                                "localIp": "10.2.2.1/24",
                                "keepaliveTime": 213,
                                "holdTime": 35,
                                "password": "23asdq",
                                "advertiseCommunity": false,
                                "advertiseExtCommunity": false
                            }]
                        }
                    }, {
                        "type": "ospf",
                        "ospfRoutes": {
                            "ospfRoute": [{
                                "id": "weq",
                                "networks": {
                                    "network": [{
                                        "ip-prefix": "10.10.10.10/24"
                                    }]
                                }
                            }, {
                                "id": "weq",
                                "networks": {
                                    "network": [{
                                        "ip-prefix": "10.10.10.10/24"
                                    }]
                                }
                            }]
                        }
                    }, {
                        "type": "static",
                        "staticRoutes": {
                            "staticRoute": [{
                                "ipPrefix": "192.16.1.1/24",
                                "nextHop": "192.16.1.121"
                            }, {
                                "ipPrefix": "192.16.1.1/24",
                                "nextHop": "192.16.1.121"
                            }]
                        }
                    }, {
                        "type": "isis",
                        "isisRoute": {
                            "networkEntity": "sasd"
                        }
                    }]
                }
            },
            "inboundQosPolicyId": "04378ca31a0d4a66b7ff2356bcf3dc43",
            "outboundQosPolicyId": "04378ca31a0d4a66b7ff2356bcf3dc12",
            "inboundQueuePolicyId": "04378ca31a0d4a66b7ff2356bcf3dc14",
            "outboundQueuePolicyId": "04378ca31a0d4a66b7ff2356bcf3dc54",
            "upstreamBandwidth": {
                "enable": true,
                "cir": 400,
                "pir": 400,
                "cbs": 400,
                "pbs": 400
            },
            "downstreamBandwidth": {
                "enable": true,
                "cir": 200,
                "pir": 200,
                "cbs": 200,
                "pbs": 200
            },
            "overrideFlows": {
                "overrideFlow": [{
                    "externalFlowId": "04378ca31a0d4a66b7ff2356bcf3dc75",
                    "name": "aabb",
                    "direction": "ingress",
                    "car": {
                        "enable": true,
                        "cir": 60,
                        "pir": 60,
                        "cbs": 60,
                        "pbs": 60
                    }
                }, {
                    "externalFlowId": "04378ca31a0d4a66b7ff2356bcf3dc20",
                    "name": "aabb",
                    "direction": "egress",
                    "car": {
                        "enable": true,
                        "cir": 60,
                        "pir": 60,
                        "cbs": 60,
                        "pbs": 60
                    }
                }]
            }
        }, {
            "id": "00000000-0000-0000-0001-000000000003",
            "name": "port3",
            "localName": "asdwe",
            "neId": "04378ca31a0d4a66b7ff2356bcf3dcea",
            "ltpId": "04378ca31a0d4a66b7ff2356bcf3dcea",
            "adminStatus": "adminUp",
            "operStatus": "operateUp",
            "description": "sdsawe",
            "tenantId": "04378ca31a0d4a66b7ff2356bcf3dcea",
            "l2Access": {
                "accessType": "dot1q",
                "dot1qVlanBitmap": "22",
                "qinqSvlanBitmap": null,
                "qinqCvlanBitmap": null,
                "accessAction": "KEEP",
                "pushVlanId": 241,
                "swapVlanId": 210
            },
            "l3Access": {
                "ipv4Address": "192.168.54.2/24",
                "routes": {
                    "route": [{
                        "type": "bgp",
                        "bgpRoutes": {
                            "bgpRoute": [{
                                "remoteAs": "weq",
                                "localAs": 32,
                                "peerIp": "13.1.1.1",
                                "localIp": "10.2.2.1/24",
                                "keepaliveTime": 213,
                                "holdTime": 35,
                                "password": "23asdq",
                                "advertiseCommunity": false,
                                "advertiseExtCommunity": false
                            }, {
                                "remoteAs": "sxca",
                                "localAs": 32,
                                "peerIp": "13.1.1.1",
                                "localIp": "10.2.2.1",
                                "keepaliveTime": 213,
                                "holdTime": 35,
                                "password": "23asdq",
                                "advertiseCommunity": false,
                                "advertiseExtCommunity": false
                            }]
                        }
                    }, {
                        "type": "static",
                        "staticRoutes": {
                            "staticRoute": [{
                                "ipPrefix": "192.16.1.1/24",
                                "nextHop": "192.16.1.121"
                            }, {
                                "ipPrefix": "192.16.1.1/24",
                                "nextHop": "192.16.1.121"
                            }]
                        }
                    }]
                }
            },
            "inboundQosPolicyId": "04378ca31a0d4a66b7ff2356bcf3dcab",
            "outboundQosPolicyId": "04378ca31a0d4a66b7ff2356bcf3dc90",
            "inboundQueuePolicyId": "04378ca31a0d4a66b7ff2356bcf3dc62",
            "outboundQueuePolicyId": "04378ca31a0d4a66b7ff2356bcf3dc14",
            "upstreamBandwidth": {
                "enable": true,
                "cir": 400,
                "pir": 400,
                "cbs": 400,
                "pbs": 400
            },
            "downstreamBandwidth": {
                "enable": true,
                "cir": 200,
                "pir": 200,
                "cbs": 200,
                "pbs": 200
            },
            "overrideFlows": {
                "overrideFlow": [{
                    "externalFlowId": "04378ca31a0d4a66b7ff2356bcf3dc09",
                    "name": "ssaa",
                    "direction": "ingress",
                    "car": {
                        "enable": true,
                        "cir": 60,
                        "pir": 60,
                        "cbs": 60,
                        "pbs": 60
                    }
                }, {
                    "externalFlowId": "04378ca31a0d4a66b7ff2356bcf3dc01",
                    "name": "ddss",
                    "direction": "egress",
                    "car": {
                        "enable": true,
                        "cir": 60,
                        "pir": 60,
                        "cbs": 60,
                        "pbs": 60
                    }
                }]
            }
        }]
    },
    "tunnelService": {
        "type": "auto-select",
        "autoSelect": {
            "loadBalanceNumber": 1
        }
    },
    "protectGroup": {
        "masterAc": "00000000-0000-0000-0001-000000000001",
        "backupAc": "00000000-0000-0000-0001-000000000002",
        "vrrp": {
            "virtualIp": "10.10.10.20",
            "vrrpTrackBfd": "vrrpTrackBfd",
            "bfdParamter": {
                "minTxInterval": 10,
                "minRxInterval": 20
            }
        }
    }
}


Response:
    Status Code: 200
    {
        "vpnId": <l3vpn Id>
    }