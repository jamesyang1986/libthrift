package com.qiezi.test.server;

import com.qiezi.test.api.HelloService;
import com.qiezi.test.impl.HelloServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftServer {
    private static Logger LOG = LoggerFactory.getLogger(ThriftServer.class);

    public static void main(String[] args) {
        int severPort = 1234;

        LOG.info("start thrift server on port:{}...", severPort);
        try {
            HelloService.Processor processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
            TServerTransport transport = new TServerSocket(severPort);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TTransportFactory());
            tArgs.minWorkerThreads(10);
            tArgs.maxWorkerThreads(20);
            TServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (Exception e) {
            LOG.error("fail to start thrift server...", e);
        }

    }
}
