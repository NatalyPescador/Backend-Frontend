import json
with open ('datos.json','r')as archivo:
    datos=json.load(archivo)
    print (datos)
datos={"cedula":"120235120","Nombre":"mia ramos",
    "Edad":"7",
    "Correo":"maia123@gmail.com",
    "profesion":"joder"}
with open ('datos.json','w')as archivo:
    json.dump(datos,archivo)
