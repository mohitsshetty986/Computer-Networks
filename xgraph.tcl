#Create a simulator object
set ns [new Simulator]

#Open the nam trace file
set nf [open out.nam w]
$ns namtrace-all $nf
#Open the output files
set f0 [open out0.tr w]

#Define a 'finish' procedure
#Define a 'finish' procedure
proc finish {} {
        global ns nf f0
        $ns flush-trace
    #Close the trace file
        close $nf
    close $f0
    #Execute nam on the trace file
        exec nam out.nam &
    exec xgraph out0.tr -geometry 800x400 &
        exit 0
}


#Create six nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

#Create a duplex link between the nodes
$ns duplex-link $n0 $n2 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
$ns duplex-link $n2 $n3 2Mb 10ms DropTail
$ns duplex-link $n3 $n4 2Mb 10ms DropTail
$ns duplex-link $n3 $n5 2Mb 10ms DropTail


#Define a procedure which periodically records the bandwidth received by the
proc record {} {
        global sink0 f0
    #Get an instance of the simulator
    set ns [Simulator instance]
    #Set the time after which the procedure should be called again
        set time 0.5
    #How many bytes have been received by the traffic sinks?
        set bw0 [$sink0 set bytes_]
    #Get the current time
        set now [$ns now]
    #Calculate the bandwidth (in MBit/s) and write it to the files
        puts $f0 "$now [expr $bw0/$time*8/1000000]"
    #Reset the bytes_ values on the traffic sinks
        $sink0 set bytes_ 0
    #Re-schedule the procedure
        $ns at [expr $now+$time] "record"
}



#Create a UDP agent and attach it to node n0
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

# Create a CBR traffic source and attach it to udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $tcp0

#Create a Null agent (a traffic sink) and attach it to node n1
set sink0 [new Agent/TCPSink]
$ns attach-agent $n4 $sink0

#Connect the traffic source with the traffic sink
$ns connect $tcp0 $sink0


#Start logging the received bandwidth
$ns at 0.0 "record"

#Schedule events for the CBR agent
$ns at 0.5 "$cbr0 start"
$ns at 4.5 "$cbr0 stop"

#Call the finish procedure after 5 seconds of simulation time
$ns at 5.0 "finish"

#Run the simulation
$ns run



