package com.example;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("com.example.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CustomAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("package com.example.annotationprocessor.generated;\n\n")
                .append("import java.util.ArrayList;\n")
                .append("public class GeneratedClass {\n\n"); // open class

        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnv.getElementsAnnotatedWith(CustomAnnotation.class)) {
            builder.append("\tpublic Object getMessage(ArrayList<String> currentCombination, String defaultValue) {\n"); // open method
            Annotation annotation = element.getAnnotation(CustomAnnotation.class);
            CustomAnnotation customAnnotation = (CustomAnnotation) annotation;
            builder.append("\t \tif (currentCombination == null) return  ").append("defaultValue")
                    .append(";\n")
                    .append("\t \tif (currentCombination.get(0) == null) return ").append("defaultValue")
                    .append(";\n")
                    .append("\t\treturn ");

            // this is appending to the return statement
            builder.append(customAnnotation.method());
        }


        builder.append(";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class


        try { // write the file
            JavaFileObject source = processingEnv.getFiler()
                    .createSourceFile("com.example.annotationprocessor.generated.GeneratedClass");


            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }


        return true;
    }
}
