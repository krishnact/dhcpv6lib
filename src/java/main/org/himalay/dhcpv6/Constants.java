/**
 Copyright (C) 2014 by
 Krishna C Tripathi, Johns Creek, GA
 All rights reserved.
 
  This file is part of DHCPv6 Library.

    DHCPv6 Library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    DHCPv6 Library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DHCPv6 Library.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.himalay.dhcpv6;

import java.net.InetAddress;


//http://tools.ietf.org/html/rfc3315
public class Constants {
	public static final InetAddress All_DHCP_Relay_Agents_and_Servers = ConstantsHelper.All_DHCP_Relay_Agents_and_Servers;
	public static final InetAddress All_DHCP_Servers                  = ConstantsHelper.All_DHCP_Servers;
	
	
	public static final int CLIENT_PORT          = 546;
	public static final int SERVER_PORT          = 547;
                                                 
	public static final int SOL_MAX_DELAY        =   1 ;//sec   Max delay of first Solicit
	public static final int SOL_TIMEOUT          =   1 ;//sec   Initial Solicit timeout
	public static final int SOL_MAX_RT           = 120 ;//secs  Max Solicit timeout value
	public static final int REQ_TIMEOUT          =   1 ;//sec   Initial Request timeout
	public static final int REQ_MAX_RT           =  30 ;//secs  Max Request timeout value
	public static final int REQ_MAX_RC           =  10 ;//      Max Request retry attempts
	public static final int CNF_MAX_DELAY        =   1 ;//sec   Max delay of first Confirm
	public static final int CNF_TIMEOUT          =   1 ;//sec   Initial Confirm timeout
	public static final int CNF_MAX_RT           =   4 ;//secs  Max Confirm timeout
	public static final int CNF_MAX_RD           =  10 ;//secs  Max Confirm duration
	public static final int REN_TIMEOUT          =  10 ;//secs  Initial Renew timeout
	public static final int REN_MAX_RT           = 600 ;//secs  Max Renew timeout value
	public static final int REB_TIMEOUT          =  10 ;//secs  Initial Rebind timeout
	public static final int REB_MAX_RT           = 600 ;//secs  Max Rebind timeout value
	public static final int INF_MAX_DELAY        =   1 ;//sec   Max delay of first Information-request
	public static final int INF_TIMEOUT          =   1 ;//sec   Initial Information-request timeout
	public static final int INF_MAX_RT           = 120 ;//secs  Max Information-request timeout value
	public static final int REL_TIMEOUT          =   1 ;//sec   Initial Release timeout
	public static final int REL_MAX_RC           =   5 ;//      MAX Release attempts
	public static final int DEC_TIMEOUT          =   1 ;//sec   Initial Decline timeout
	public static final int DEC_MAX_RC           =   5 ;//      Max Decline attempts
	public static final int REC_TIMEOUT          =   2 ;//secs  Initial Reconfigure timeout
	public static final int REC_MAX_RC           =   8 ;//      Max Reconfigure attempts
	public static final int HOP_COUNT_LIMIT      =  32 ;//      Max hop count in a Relay-forward message
	                                             
	public static final int INFINITY             = 0xffffffff;
	
	public static final short MSGTYPE_SOLICIT              = 1  ;
	public static final short MSGTYPE_ADVERTISE            = 2  ;
	public static final short MSGTYPE_REQUEST              = 3  ;
	public static final short MSGTYPE_CONFIRM              = 4  ;
	public static final short MSGTYPE_RENEW                = 5  ;
	public static final short MSGTYPE_REBIND               = 6  ;
	public static final short MSGTYPE_REPLY                = 7  ;
	public static final short MSGTYPE_RELEASE              = 8  ;
	public static final short MSGTYPE_DECLINE              = 9  ;
	public static final short MSGTYPE_RECONFIGURE          = 10 ;
	public static final short MSGTYPE_INFORMATION_REQUEST  = 11 ;
	public static final short MSGTYPE_RELAY_FORW           = 12 ;
	public static final short MSGTYPE_RELAY_REPL           = 13 ;	
	
	public static final int OPTION_CLIENTID      = 1  ;
	public static final int OPTION_SERVERID      = 2  ;
	public static final int OPTION_IA_NA         = 3  ;
	public static final int OPTION_IA_TA         = 4  ;
	public static final int OPTION_IAADDR        = 5  ;
	public static final int OPTION_ORO           = 6  ;
	public static final int OPTION_PREFERENCE    = 7  ;
	public static final int OPTION_ELAPSED_TIME  = 8  ;
	public static final int OPTION_RELAY_MSG     = 9  ;
	public static final int OPTION_AUTH          = 11 ;
	public static final int OPTION_UNICAST       = 12 ;
	public static final int OPTION_STATUS_CODE   = 13 ;
	public static final int OPTION_RAPID_COMMIT  = 14 ;
	public static final int OPTION_USER_CLASS    = 15 ;
	public static final int OPTION_VENDOR_CLASS  = 16 ;
	public static final int OPTION_VENDOR_OPTS   = 17 ;
	public static final int OPTION_INTERFACE_ID  = 18 ;
	public static final int OPTION_RECONF_MSG    = 19 ;
	public static final int OPTION_RECONF_ACCEPT = 20 ;
	public static final int OPTION_IA_PREFIX_DEL = 25 ;
	
	/**
	 * Status codes
	 */
	public static final int STATUS_Success       = 0;// Success.
	public static final int STATUS_UnspecFail    = 1;/* Failure, reason unspecified; this
										                    status code is sent by either a client
										                    or a server to indicate a failure
										                    not explicitly specified in this
										                    document.*/
	public static final int STATUS_NoAddrsAvail  = 2;/* Server has no addresses available to assign to
	                     									the IA(s). */
	public static final int STATUS_NoBinding     = 3;/* Client record (binding) unavailable. */
	public static final int STATUS_NotOnLink     = 4;/* The prefix for the address is not appropriate for
	                     									the link to which the client is attached. */
	public static final int STATUS_UseMulticast  = 5;/* Sent by a server to a client to force the
										                    client to send messages to the server.
										                    using the All_DHCP_Relay_Agents_and_Servers
										                    address.*/

}
