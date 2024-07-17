from flask import Flask,jsonify,request
from flask_mysqldb import MySQL
from config import config
app=(Flask(__name__))
conexion=MySQL(app)

@app.route('/listartareas',methods=['GET'])
def listartareas():
  try:
    #crear la conexion 
    cursor=conexion.connection.cursor()
    sql="SELECT * FROM tareas"
    cursor.execute(sql)
    datos = cursor.fetchall()
    print (datos)
    tareas=[]
    for fila in datos:
      #creamos el diccionario
      tarea={
        'Idtar':fila[0],
        'Nombretar':fila[1],
        'FechaInicio':str(fila[2]),
        'FechaFin':str(fila[3]),
        'Estado':fila[4]
      }
      tareas.append(tarea)
    return jsonify({'tareas':tareas,'Mensaje':"Listado de tareas","exito":True})    
  except Exception as ex:
      return'no se pudo mi hermano'
@app.route('/listarusuarios',methods=['GET'])
def listarusuarios():
  try:
    #crear la conexion 
    cursor=conexion.connection.cursor()
    usuario="SELECT * FROM usuario"
    cursor.execute(usuario)
    consulta = cursor.fetchall()
    print (consulta)
    usuarios=[]
    for consulta in consulta:
      #creamos el diccionario
      usu={
        'Idusu':consulta[0],
        'Nombre':consulta[1],
        'Apellido':consulta[2],
        'Email':consulta[3],
        'Usuario':consulta[4],
        'Contraseña':consulta[5],
        'Rol':consulta[6],
      }
      usuarios.append(usu)
    return jsonify({'usuarios':usuarios,'Mensaje':"Listado de usarios","exito":True})    
  except Exception as ex:
      return'no se pudo'
@app.route('/buscartareas',methods=['GET'])
def buscartareas():
  consulta= "SELECT * FROM tareas"
  filtro= []
  parametros=[]
  
  nombre=request.args.get('Nombretar')
  if nombre:
    filtro.append("Nombretar LIKE %s")
    parametros.append(f"%{nombre}%")
  if not filtro: 
    return jsonify({'message':'No tiene parametros la busqueada'}),400
  
  consulta +=" WHERE "+" AND ".join(filtro)
  cursor=conexion.connection.cursor()
  cursor.execute(consulta,parametros)
  datos = cursor.fetchall()
  tareas=[]
  for fila in datos:
      #creamos el diccionario
      tarea={
        'Idtar':fila[0],
        'Nombretar':fila[1],
        'FechaInicio':str(fila[2]),
        'FechaFin':str(fila[3]),
        'Estado':fila[4]
      }
      tareas.append(tarea)
      return jsonify({'tareas':tareas,'Mensaje':"Listado de tarea","exito":True}) 
      
@app.route('/buscarusuarios',methods=['GET'])
def buscarusuarios():
    consulta ='SELECT * FROM usuario'
    filtro = []
    parametros= []

    Usuario = request.args.get('Usuario')
    if Usuario:
        filtro.append("Usuario LIKE %s")
        parametros.append(f"%{Usuario}%")

    id_usu= request.args.get('id_usu')
    if id_usu:
        filtro.append("id_usu LIKE %s")
        parametros.append(f"%{id_usu}%")

    Email= request.args.get('Email')
    if Email:
        filtro.append("Email LIKE %s")
        parametros.append(f"%{Email}%")

    if not filtro:
        return jsonify({'message ':'no tiene parametros la busqueda '}),400
    consulta += " WHERE " + " AND ".join(filtro)
    cursor = conexion.connection.cursor()
    cursor.execute(consulta, parametros)
    datos = cursor .fetchall()
    tareas= []
    for fila in datos:
            #crear el diccionario
            tarea = {
                'id_usu':fila[0],
                'Nombre':fila[1],
                'Apellido':fila[2],
                'Email':fila[3],
                'Usuario': fila[4],
                'Rol':fila[6]
            }
            tareas.append(tarea)
    return jsonify(tareas) 
@app.route('/actualizartarea/<Idtar>',methods=['PUT'])
def actualizartarea(Idtar):
  actu=request.json
  Nombretar=actu['Nombretar']
  FechaInicio=actu['FechaInicio']
  FechaFin=actu['FechaFin']
  Estado=actu['Estado']
  cursor=conexion.connection.cursor()
  cursor.execute("""UPDATE tareas SET Nombretar=%s,FechaInicio=%s,FechaFin=%s,Estado=%s WHERE Idtar=%s""",(Nombretar,FechaInicio,FechaFin,Estado,Idtar))
  conexion.connection.commit()
  return jsonify({'message':'tarea actualizada:))'}),201
@app.route('/actualizarusuario/<id_usu>',methods=['PUT'])
def actualizarusuario(id_usu):
  actu=request.json
  
  Nombre=actu['Nombre']
  Apellido=actu['Apellido']
  Email=actu['Email']
  Usuario=actu['Usuario']
  Rol=actu['Rol']
  cursor=conexion.connection.cursor()
  cursor.execute("""UPDATE usuario SET Nombre=%s,Apellido=%s,Email=%s,Usuario=%s, Rol=%s WHERE id_usu=%s""",(Nombre,Apellido,Email,Usuario,Rol,id_usu))
  conexion.connection.commit()
  return jsonify({'message':'usuario actualizado:))'}),201
@app.route('/creartarea',methods=['POST'])
def creartarea():
  tarea=request.json
  Nombretar=tarea['Nombretar']
  FechaInicio=tarea['FechaInicio']
  FechaFin=tarea['FechaFin']
  Estado=tarea['Estado']
  cursor=conexion.connection.cursor()
  cursor.execute('INSERT INTO tareas(Nombretar,FechaInicio,FechaFin,Estado)VALUES(%s,%s,%s,%s)',(Nombretar,FechaInicio,FechaFin,Estado))
  conexion.connection.commit()
  return jsonify({'message':'Tarea agregada con exito'}),201
@app.route('/crearusuario',methods=['POST'])
def crearusuario():
  usu=request.json
  Nombre=usu['Nombre']
  Apellido=usu['Apellido']
  Email=usu['Email']
  Usuario=usu['Usuario']
  Rol=usu['Rol']
  cursor=conexion.connection.cursor()
  cursor.execute('INSERT INTO usuario(Nombre,Apellido,Email,Usuario,Rol)VALUES(%s,%s,%s,%s,%s)',(Nombre,Apellido,Email,Usuario,Rol))
  conexion.connection.commit()
  return jsonify({'message':'Usuario agregado con exito'}),201
@app.route('/deletetarea/<Idtar>',methods=['DELETE'])
def deletetarea(Idtar):
  cursor=conexion.connection.cursor()
  cursor.execute('DELETE FROM tareas WHERE Idtar = %s',[Idtar])
  conexion.connection.commit()
  return jsonify({'message':'Tarea eliminada con exito'}),201
@app.route('/deleteusuario/<id_usu>',methods=['DELETE'])
def deleteusuario(id_usu):
  cursor=conexion.connection.cursor()
  cursor.execute('DELETE FROM usuario WHERE id_usu = %s',[id_usu])
  conexion.connection.commit()
  return jsonify({'message':'Usuaro eliminado con exito'}),201
if __name__=='__main__':
  app.config.from_object(config['config'])
  app.run(debug=True)