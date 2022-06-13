import sys

from pyspark.sql import SparkSession
from pyspark.sql.functions import col, expr

if __name__ == '__main__':
  print("Starting spark program")
  print('Number of arguments:', len(sys.argv), 'arguments.')
  print('Argument List:', str(sys.argv))
  spark = SparkSession.builder.appName('AddMonths').getOrCreate()
  data = [("2019-01-23", 1), ("2019-06-24", 2), ("2019-09-20", 3)]
  spark.createDataFrame(data).toDF("date", "increment") \
    .select(col("date"), col("increment"), expr(
              "add_months(to_date(date,'yyyy-MM-dd'),cast(increment as int))").alias(
              "inc_date")) \
    .show()
  print("Finished spark program")
