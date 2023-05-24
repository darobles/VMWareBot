# Description:
#   Example scripts for you to examine and try out.
#
# Notes:
#   They are commented out by default, because most of them are pretty silly and
#   wouldn't be useful and amusing enough for day to day huboting.
#   Uncomment the ones you want to try and experiment with.
#
#   These are from the scripting documentation: https://github.com/github/hubot/blob/master/docs/scripting.md

module.exports = (robot) ->
	 
   robot.respond /ping (.*)$/i, (res) ->
    hostname = res.match[1]
    @exec = require('child_process').exec
    command = "ping #{hostname}"

    res.send "Looking up #{hostname}..."
    res.send "This is the command #{command}."

    @exec command, (error, stdout, stderr) ->
      res.send error
      res.send stdout
      res.send stderr
	  
	  
   robot.hear /lista vms(.*)/i, (res) ->
     res.send "@" + res.message.user.name.toLowerCase() + "	esta es la lista de VMS" 
     res.http("http://<server>/terraform/vms")
       .get() (error, response, body) ->
        try
         res.send body
        catch error
         res.send "Algo salio inesperadamente mal..."  

   robot.hear /destruye vm (.*)/i, (res) ->
     vmname = escape(res.match[1])
     res.send "@" + res.message.user.name.toLowerCase() +  "Estoy destruyendo la VM  #{vmname} este proceso demora 1min aprox, te avisare cuando este listo :)"
     res.http("http://<server>/terraform/destroy/" + vmname)
       .get() (error, response, body) ->
        try
         res.send "@" + res.message.user.name.toLowerCase() + " " + body
        catch error
         res.send "Algo salio inesperadamente mal..."  		
		 
   robot.hear /crea vm (.*)/i, (res) ->
     vmname = escape(res.match[1])
     res.send "@" + res.message.user.name.toLowerCase() + " Estoy creando la VM #{vmname} este proceso demora 13min aprox, te avisare cuando este lista :)"
     res.http("http://<server>/terraform/create/" + vmname)
       .get() (error, response, body) ->
        try
         res.send "@" + res.message.user.name.toLowerCase() + " " + body
        catch error
         res.send "Algo salio inesperadamente mal..."  	

   robot.hear /auterito ayuda/i, (res) ->
      res.send "Mis comandos son:\n*lista vms*: muestra todas las maquinas virtuales creadas actualmente. \n*crea vm <nombre>*: Crea una maquina virtual con el nombre dado. \n*destruye vm <nombre>*: Destruye la maquina virtual con el nombre dado. Esta acción no puede ser revertida. "			 
