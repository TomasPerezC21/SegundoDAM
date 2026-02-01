import json

class Producto:
    def __init__(self, referencia, nombre, precio, stock):
        self.referencia = referencia
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def vender(self, cantidad):
        if cantidad <= self.stock:
            self.stock -= cantidad
            print(f"Venta: {cantidad} unidades de {self.nombre}. Stock restante: {self.stock}")
        else:
            print(f"[ERROR] Stock insuficiente para {self.nombre}")

    def reponer(self, cantidad):
        self.stock += cantidad
        print(f"Repuesto: {cantidad} unidades de {self.nombre}. Nuevo stock: {self.stock}")

class Almacen:
    def __init__(self):
        self.lista_productos = [] 
        self.cargar_datos()       # Arranque automático 
    def añadir_producto(self, producto):
        self.lista_productos.append(producto)
        self.guardar_datos()      # Guardado automático al modificar

    def guardar_datos(self):
        # Transformación: Objetos -> Diccionarios usando __dict__ 
        lista_dicts = [p.__dict__ for p in self.lista_productos]
        
        with open("inventario.json", "w") as archivo:
            # Escritura con indentación para legibilidad 
            json.dump(lista_dicts, archivo, indent=4)
        print("--> Datos guardados en inventario.json")

    def cargar_datos(self):
        try:
            # Robustez: Intenta abrir el archivo 
            with open("inventario.json", "r") as archivo:
                lista_dicts = json.load(archivo)
                
                self.lista_productos = []
                # Reconstrucción: Diccionarios -> Objetos funcionales 
                for d in lista_dicts:
                    # Se pasan los argumentos en orden: referencia, nombre, precio, stock
                    nuevo_p = Producto(d["referencia"], d["nombre"], d["precio"], d["stock"])
                    self.lista_productos.append(nuevo_p)
            print("--> Datos cargados correctamente del inventario.")
            
        except FileNotFoundError:
            # Si no existe (primera vez), inicia lista vacía sin explotar 
            print("--> No se encontró inventario previo. Iniciando almacén vacío.")
            self.lista_productos = []

# --- BLOQUE DE PRUEBA Y VALIDACIÓN ---
if __name__ == "__main__":
    tienda = Almacen()

    # --- PASO 1: Ejecuta esto una vez para crear datos ---
    
    p1 = Producto("REF001", "Monitor 24", 150.00, 10)
    p2 = Producto("REF002", "Teclado USB", 20.00, 50)
    
    #tienda.añadir_producto(p1)
    #tienda.añadir_producto(p2)
    
    print(f"Productos en almacén: {len(tienda.lista_productos)}")

    # --- PASO 2: Verificación de métodos ---
    if len(tienda.lista_productos) > 0:
        print("\nProbando método vender en el primer producto:")
        tienda.lista_productos[0].vender(2) # Modifica el stock
        tienda.guardar_datos() # Guarda el cambio de stock