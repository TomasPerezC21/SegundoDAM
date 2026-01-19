import sqlite3

# --- FASE 1: ARQUITECTURA DE CLASES ---

class Entidad:
    def __init__(self, nombre, email):
        self.nombre = nombre
        self.email = email  

class Cliente(Entidad):
    def __init__(self, nombre, email, credito):
        # Uso obligatorio de super() para inicializar atributos base 
        super().__init__(nombre, email)
        self.credito = credito  

    def guardar_en_db(self):
        # El objeto persiste su propia información 
        # Conexión específica para la operación de guardado
        conn = sqlite3.connect("contactos.db")
        cursor = conn.cursor()
        
        # Insertar datos usando self y parámetros seguros (?) 
        cursor.execute('''
            INSERT INTO clientes (nombre, email, credito) 
            VALUES (?, ?, ?)
        ''', (self.nombre, self.email, self.credito))
        
        conn.commit()
        conn.close()
        print("[SISTEMA] Cliente guardado correctamente en la base de datos.")

class Proveedor(Entidad):
    def __init__(self, nombre, email, nombre_comercial):
        super().__init__(nombre, email)
        self.nombre_comercial = nombre_comercial  


# --- BLOQUE PRINCIPAL (MAIN) ---
if __name__ == "__main__":
    
    # 1. PREPARACIÓN DE LA BASE DE DATOS (FASE 3)
    # Conexión y creación automática de la tabla si no existe 
    conn = sqlite3.connect("contactos.db")
    cursor = conn.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS clientes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT,
            email TEXT,
            credito REAL
        )
    ''')
    conn.commit()
    conn.close()

    # 2. INTERACCIÓN CON EL USUARIO (FASE 2)
    print("--- ALTA DE NUEVO CLIENTE ---")
    # Solicitar datos por teclado [cite: 93]
    nombre_in = input("Nombre: ")
    email_in = input("Email: ")
    # Convertir crédito a float para manejo numérico
    credito_in = float(input("Crédito (€): "))

    # 3. CREACIÓN Y GUARDADO
    # Instanciar el objeto Cliente con los datos capturados 
    nuevo_cliente = Cliente(nombre_in, email_in, credito_in)
    
    # El objeto se guarda a sí mismo 
    nuevo_cliente.guardar_en_db()