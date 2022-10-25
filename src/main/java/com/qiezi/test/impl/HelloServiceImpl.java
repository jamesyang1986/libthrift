package com.qiezi.test.impl;

import com.qiezi.test.api.HelloService;
import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "thrift server say hello:" + username;
    }

    @Override
    public String sayBye(String username) throws TException {
        return "thrift server say bye:" + username;
    }
}
