#Create a simulator object
set ns [new Simulator]

#$ns rtproto Static
#Define different colors for data flows
$ns color 1 Blue
$ns color 2 Red

#Open the Trace file
set file1 [open out.tr w]
$ns trace-all $file1

#Open the NAM trace file
set file2 [open out.nam w]
$ns namtrace-all $file2

#Define a 'finish' procedure
proc finish {} {
        global ns file1 file2
        $ns flush-trace
        close $file1
        close $file2
        exec nam out.nam &
        exit 0
}
#Create four nodes
#for {set i 1}{i<= 4}{incr i}{
#set n($i) [$ns node]
#}
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
set n7 [$ns node]
set n8 [$ns node]

#Create links between the nodes
############################################################
$ns duplex-link $n0 $n2 1Mb 5ms DropTail
$ns duplex-link $n1 $n2 1Mb 5ms DropTail

$ns duplex-link $n2 $n6 5Mb 5ms SFQ #1st
$ns duplex-link $n6 $n7 5Mb 10ms SFQ
$ns duplex-link $n3 $n7 5Mb 5ms SFQ

$ns duplex-link $n3 $n2 5Mb 10ms SFQ  #2nd

$ns duplex-link $n2 $n8 5Mb 5ms SFQ #3rd
$ns duplex-link $n8 $n3 5Mb 5ms SFQ

$ns duplex-link $n3 $n4 1Mb 5ms DropTail
$ns duplex-link $n3 $n5 1Mb 5ms DropTail
##########################################################
$ns duplex-link-op $n0 $n2 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up

$ns duplex-link-op $n2 $n6 orient up
$ns duplex-link-op $n6 $n7 orient right
$ns duplex-link-op $n3 $n7 orient up

$ns duplex-link-op $n2 $n3 orient right

$ns duplex-link-op $n2 $n8 orient right-down
$ns duplex-link-op $n8 $n3 orient right-up

$ns duplex-link-op $n3 $n4 orient right-up
$ns duplex-link-op $n3 $n5 orient right-down

#########################################################
#Monitor the queue for the link between node 2 and node 3
$ns duplex-link-op $n2 $n3 queuePos 0.5

#Create a UDP agent and attach it to node n0
set udp0 [new Agent/UDP]
$udp0 set class_ 1
$ns attach-agent $n0 $udp0

# Create a CBR traffic source and attach it to udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $udp0

#Setup a TCP connection
set tcp [new Agent/TCP/Newreno]
$ns attach-agent $n1 $tcp
set sink [new Agent/TCPSink/DelAck]
$ns attach-agent $n5 $sink
$ns connect $tcp $sink
$tcp set fid_ 2

#Setup a FTP over TCP connection
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

#Create a Null agent (a traffic sink) and attach it to node n3
set null0 [new Agent/Null]
$ns attach-agent $n4 $null0

#Connect the traffic sources with the traffic sink
$ns connect $udp0 $null0  

#Schedule events for the CBR agents
$ns at 0.2 "$cbr0 start"

$ns at 0.1 "$ftp start"
$ns rtmodel-at 1.0 down $n2 $n3
$ns rtmodel-at 1.5 down $n2 $n8
$ns rtmodel-at 2.5 up $n2 $n3
$ns rtmodel-at 2.0 up $n2 $n8
$ns at 5.0 "$cbr0 stop"
#Call the finish procedure after 5 seconds of simulation time
$ns at 5.0 "$ftp stop"
$ns at 5.2 "finish"

#Run the simulation
$ns run



