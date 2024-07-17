import json
#Leer la api
with open ('./Guia/ejerciciodatos.json','r')as archivo:
    datos=json.load(archivo)
    print (datos)
#Incrementar el valor de stock
cambiar={"producto": "Laptop",
    "precio": 750,
    "stock": 20+5}
#Escribir la modificacon a nuevos_datos.son
with open ('./Guia/nuevos_datos.json','w')as archivo:
    json.dump(cambiar,archivo)  

