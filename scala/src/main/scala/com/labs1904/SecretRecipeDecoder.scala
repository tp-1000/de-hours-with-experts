package com.labs1904

import java.io.{BufferedWriter, File, FileWriter, IOException}
import java.nio.file.{Files, Path}
import java.util.Scanner
import scala.collection.immutable.HashMap
import scala.io.Source

/**
 * An ingredient has an amount and a description.
 * @param amount For example, "1 cup"
 * @param description For example, "butter"
 */
case class Ingredient(amount: String, description: String) {

  override def toString: String = {
    amount + " " + description + "\n"
  }

}

object SecretRecipeDecoder {
  val ENCODING: Map[String, String] = HashMap[String, String](
    "y" -> "a",
    "h" -> "b",
    "v" -> "c",
    "x" -> "d",
    "k" -> "e",
    "p" -> "f",
    "z" -> "g",
    "s" -> "h",
    "a" -> "i",
    "b" -> "j",
    "e" -> "k",
    "w" -> "l",
    "u" -> "m",
    "q" -> "n",
    "n" -> "o",
    "l" -> "p",
    "m" -> "q",
    "f" -> "r",
    "o" -> "s",
    "i" -> "t",
    "g" -> "u",
    "j" -> "v",
    "t" -> "w",
    "d" -> "x",
    "r" -> "y",
    "c" -> "z",
    "3" -> "0",
    "8" -> "1",
    "4" -> "2",
    "0" -> "3",
    "2" -> "4",
    "7" -> "5",
    "5" -> "6",
    "9" -> "7",
    "1" -> "8",
    "6" -> "9"
  )

  /**
   * Given a string named str, use the Caeser encoding above to return the decoded string.
   * @param str A caesar-encoded string.
   * @return
   */
  def decodeString(str: String): String  = {

    str.map(x => ENCODING.getOrElse(x.toString, x.toString)).mkString

    // the issue is if the value is not there I want it to not decode and leave the same
    // go through the list if there is am

  }

  /**
   * Given an ingredient, decode the amount and description, and return a new Ingredient
   * @param line An encoded ingredient.
   * @return
   */
  def decodeIngredient(line: String): Ingredient = {

    val amountAndDescriptionSplit : Array[String] = decodeString(line).split("#")
    new Ingredient(amountAndDescriptionSplit(0), amountAndDescriptionSplit(1))

  }


  /**
   * A program that decodes a secret recipe
   *
   * @param args
   */
  def decodeFile(): Unit = {
      val file = new File("decoded.txt")
      val bw = new BufferedWriter(new FileWriter(file))

      //read file
      val filename = "src/main/resources/secret_recipe.txt"
      //write them out decoded
      Source.fromFile(filename).getLines.foreach(x => bw.write(decodeIngredient(x).toString))
      bw.close()
  }



  def main(args: Array[String]): Unit = {
    // TODO: implement me
    decodeFile()
  }
}
